package com.project.shop.service.impl.base;

import com.project.shop.dao.attr.TrademarkMapper;
import com.project.shop.global.controller.FileGlobalConstant;
import com.project.shop.global.service.TrademarkConstant;
import com.project.shop.pojo.bo.LimitPageOfTrademarkBO;
import com.project.shop.pojo.po.attr.Trademark;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.service.inter.base.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class TrademarkServiceImpl implements TrademarkService {

    private TrademarkMapper trademarkMapper;

    @Override
    public LimitPageOfTrademarkBO getLimitPage(Integer currentPage, Integer pageLimit) {
        LimitPageOfTrademarkBO limitPageOfTrademarkBO=new LimitPageOfTrademarkBO();
        if (currentPage<1) currentPage=1;
        if (pageLimit<1) pageLimit=1;
        List<Trademark> trademarkList=trademarkMapper.getLimitPage((currentPage-1)*pageLimit,pageLimit);
        limitPageOfTrademarkBO.setTrademarkList(trademarkList);
        limitPageOfTrademarkBO.setCurrentPage(currentPage);
        limitPageOfTrademarkBO.setPageLimit(pageLimit);
        Integer itemsTotal=trademarkMapper.getItemsTotal();
        if (itemsTotal==null) itemsTotal=0;
        limitPageOfTrademarkBO.setItemsTotal(itemsTotal);
        Integer pagesTotal=itemsTotal/pageLimit;
        if (itemsTotal%pageLimit!=0)  pagesTotal++;
        limitPageOfTrademarkBO.setPagesTotal(pagesTotal);
        return limitPageOfTrademarkBO;
    }

    @Override
    public Trademark getTrademarkByTmName(String tmName) {
        return trademarkMapper.getTrademarkByTmName(tmName);
    }

    @Override
    public Trademark getTrademarkById(Integer id) {
        return trademarkMapper.getTrademarkById(id);
    }

    @Override
    public String addTrademark(Trademark trademark) {
        String result;
        if (trademark.getTmName()!=null && !trademark.getTmName().trim().equals("")){
            Trademark selectTrademark = trademarkMapper.getTrademarkByTmName(trademark.getTmName());
            if (selectTrademark==null){
                // url??????,?????????????????????
                if (trademark.getLogoUrl()==null || trademark.getTmName().trim().equals("")) trademark.setLogoUrl(TrademarkConstant.DefaultLogoUrl);
                Integer insertResult = trademarkMapper.addTrademarkWithoutId(trademark);
                if (insertResult==1) result=TrademarkConstant.InsertSuccess;
                else result=TrademarkConstant.InsertFailed;
            }else {
                result=TrademarkConstant.InsertFailedBecauseNoOnlyTmName;
            }
        }else {
            result=TrademarkConstant.InsertFailedBecauseEmptyTmName;
        }
        System.out.println("trademarkService????????????:"+result);
        return result;
    }

    @Override
    public String updateTrademarkById(Trademark trademark) {
        // SQL???????????????,where?????????id?????????null,????????????????????????
        String result;
        if (trademark.getTmName()!=null && !trademark.getTmName().trim().equals("")){
            if (trademark.getLogoUrl()==null || trademark.getLogoUrl().trim().equals("")){
                trademark.setLogoUrl(TrademarkConstant.DefaultLogoUrl);
            }
            if (trademark.getId()!=null) {
                Trademark selectTrademark=trademarkMapper.getTrademarkByTmName(trademark.getTmName());
                if (selectTrademark==null || Objects.equals(selectTrademark.getId(), trademark.getId())){
                    trademarkMapper.updateTrademarkById(trademark);
                    result=TrademarkConstant.UpdateSuccess;
                }else {
                    result=TrademarkConstant.UpdateFailedBecauseNoOnlyTmName;
                }
            }else {
                result=TrademarkConstant.UpdateFailedBecauseEmptyId;
            }
        }else {
            result=TrademarkConstant.UpdateFailedBecauseEmptyTmName;
        }
        return result;
    }

    @Override
    public Integer deleteTrademarkById(Integer id) {
        return trademarkMapper.deleteTrademarkById(id);
    }

    @Override
    public AboutAdminResponse fileUploadForTrademarkLogoImg(String tmName, MultipartFile file, HttpServletRequest request) {
        AboutAdminResponse response=new AboutAdminResponse();
        String message;
        Integer code;
        String realLocalDirPath = FileGlobalConstant.ProjectImgLocalURL + "\\" + FileGlobalConstant.TrademarkLogoImgLocalFileFolder;
        // ??????????????????????????????????????????????????????,??????????????????????????????????????????????????????
        // ??????????????????,??????????????????????????????,???????????????????????????,?????????????????????
        String temporaryDirPath=request.getServletContext().getRealPath("/"+FileGlobalConstant.TrademarkLogoImgServerFileFolder);
        System.out.println("??????????????????:"+realLocalDirPath);
        System.out.println("?????????????????????????????????:"+temporaryDirPath);

        // ???????????????????????????,??????????????????????????????????????????,???????????????
        File filePath=new File(realLocalDirPath);
        if (!filePath.exists()){
            filePath.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf(".")+1);//?????????
        String newFilename=tmName+"."+suffix;   //+"_"+ UUID.randomUUID()+"_"+originalFilename;
        try {
            File temporaryFile=new File(temporaryDirPath+"\\"+newFilename);
            File realLocalFile=new File(realLocalDirPath+"\\"+newFilename);

            // transferTo?????????????????????,???????????????,????????????????????????????????????????????????????????????????????????,????????????????????????
            InputStream inputStream = file.getInputStream();
            FileOutputStream temporaryOutputStream = new FileOutputStream(temporaryFile);
            FileOutputStream fileOutputStream = new FileOutputStream(realLocalFile);
            byte[] buff = new byte[1024];
            int len;
            while ((len=inputStream.read(buff))!=-1){
                temporaryOutputStream.write(buff,0,len);
                fileOutputStream.write(buff,0,len);
            }
            inputStream.close();
            temporaryOutputStream.close();
            fileOutputStream.close();

            code= FileGlobalConstant.PassCode;
            message="??????????????????";
            response.setData(FileGlobalConstant.ServerAddress + "/" + FileGlobalConstant.TrademarkLogoImgServerFileFolder+"/"+newFilename);
        } catch (IOException e) {
            code=FileGlobalConstant.ForbiddenCode;
            message="??????????????????";
            throw new RuntimeException(e);
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    @Override
    public List<Trademark> getTrademarkList() {
        return trademarkMapper.getTrademarkList();
    }

    @Autowired
    public void setTrademarkMapper(TrademarkMapper trademarkMapper) {
        this.trademarkMapper = trademarkMapper;
    }
}
