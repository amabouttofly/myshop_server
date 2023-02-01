package com.project.shop.service.impl.base;

import com.project.shop.dao.spu.SpuImageMapper;
import com.project.shop.dao.spu.SpuMapper;
import com.project.shop.dao.spu.SpuSaleAttrKeyMapper;
import com.project.shop.dao.spu.SpuSaleAttrValueMapper;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.FileGlobalConstant;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.pojo.bo.LimitPageOfSpuBo;
import com.project.shop.pojo.po.spu.*;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.pojo.sql.DeleteSpuSaleAttrValueSqlParam;
import com.project.shop.service.inter.base.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Transactional
@Service
public class SpuServiceImpl implements SpuService {

    private SpuMapper spuMapper;
    private SpuSaleAttrKeyMapper spuSaleAttrKeyMapper;
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    private SpuImageMapper spuImageMapper;

    @Override
    public LimitPageOfSpuBo getSpuListLimitPage(Integer currentPage, Integer pageLimit, Integer category3Id) {
        LimitPageOfSpuBo limitPageOfSpuBo = new LimitPageOfSpuBo();
        Integer itemsTotal = spuMapper.getSpuListItemTotalByCategory3Id(category3Id);
        if (itemsTotal==null) itemsTotal=0;
        if (pageLimit < 1) pageLimit = 1;
        if (currentPage < 1) currentPage = 1;

        if (pageLimit > itemsTotal) pageLimit = itemsTotal;
        if (itemsTotal > 0){
            while (pageLimit * (currentPage - 1) >= itemsTotal){
                currentPage--;
            }
        }
        Integer index = (currentPage - 1) * pageLimit;
        Integer size = pageLimit;
        limitPageOfSpuBo.setSpuList(spuMapper.getSpuListLimitPageByCategory3Id(index, size, category3Id));
        limitPageOfSpuBo.setCurrentPage(currentPage);
        limitPageOfSpuBo.setPageLimit(pageLimit);
        limitPageOfSpuBo.setItemsTotal(itemsTotal);

        Integer pagesTotal;
        if (pageLimit == 0){
            pagesTotal = 0;
        }else {
            pagesTotal=itemsTotal/pageLimit;
            if (itemsTotal%pageLimit!=0)  pagesTotal++;
        }
        limitPageOfSpuBo.setPagesTotal(pagesTotal);
        return limitPageOfSpuBo;
    }

    @Override
    public Spu getSpuById(Integer spuId) {
        Spu spu = spuMapper.getSpuById(spuId);
        if (spu != null){
            // 只要查询结果spu不为空,则一定回应对手的数组属性,且里面至少有一个对象元素
            if (spu.getSpuImageList().get(0).getId() == null){
                spu.getSpuImageList().clear();
            }
            if (spu.getSpuSaleAttrKeyList().get(0).getId() == null){
                spu.getSpuSaleAttrKeyList().clear();
            }
        }
        return spu;
    }


    @Override
    public List<SaleAttrKey> getSaleAttrKeyList() {
        return spuMapper.getSaleAttrKeyList();
    }

    @Override
    public Spu getSpuBySpuName(String spuName) {
        return spuMapper.getSpuBySpuName(spuName);
    }

    @Override
    public AboutAdminResponse saveSpuInfo(Spu spu){
        AboutAdminResponse response;
        if (spu.getSpuName() == null
                || spu.getSpuName().trim().equals("")
                || spu.getDescription() == null
                || spu.getCategory3Id() == null
                || spu.getTmId() == null) {
            response = new AboutAdminResponse();
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setData("spu信息不完整");
            return response;
        }
        if (spu.getId() == null){
            System.out.println("执行spu的插入操作,返回的data中为spu的新id");
            response = addSpu(spu);
        }else {
            System.out.println("执行spu更新操作");
            response = updateSpu(spu);
        }
        return response;
    }

    public AboutAdminResponse updateSpu(Spu spu) {
        AboutAdminResponse response = new AboutAdminResponse();
        Spu checkRepeatSpu = spuMapper.getSpuBySpuNameAndCategory3Id(spu);
        if (checkRepeatSpu != null && !Objects.equals(checkRepeatSpu.getId(), spu.getId())){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("同一个三级分类下,SPU名称不能重复");
            return response;
        }
        if (spuMapper.updateSpuById(spu) == 0) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("SPU更新失败,spuId可能不存在");
            return response;
        }
        if (spu.getSpuSaleAttrKeyList() == null || spu.getSpuSaleAttrKeyList().size() == 0){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("SPU提交成功");
            return response;
        }
        System.out.println("开始插入,删除SPU的新属性");
        // 不存在更新操作,在用户界面,用户删除a属性,再添加a属性,此时a属性对象对应数据库中的id丢失了,无法定位
        // 先删除不存在于请求数据spu对象中的key对象数组,数据库中value表使用了联机删除,因此只用删除key
        List<SpuSaleAttrKey> spuSaleAttrKeyList = updateSpuSaleAttrKeyList(spu);
        System.out.println("key对象更新结束,得到的key对象数组为:"+spuSaleAttrKeyList);
        if (!updateSpuSaleAttrValueFromKeyList(spuSaleAttrKeyList)) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("提交失败");
            return response;
        }
        System.out.println("开始执行删除不存在的spu图片操作,只存在于更新spu操作中");
        Integer deleteImageNum;
        if (spu.getSpuImageList() == null || spu.getSpuImageList().size() == 0) {
            deleteImageNum = spuImageMapper.deleteSpuImageBySpuId(spu.getId());
        }else {
            deleteImageNum = spuImageMapper.deleteSpuImageBySpu(spu);
        }
        System.out.println("删除图片对象的个数为:"+deleteImageNum);
        response.setCode(AdminStaticData.PassCode);
        response.setMessage("提交成功");
        return response;
    }

    public AboutAdminResponse addSpu(Spu spu) {
        AboutAdminResponse response = new AboutAdminResponse();
        // 只匹配category3Id,spuName字段.因此只有在category3Id相同时,spuName才唯一
        Spu checkRepeatSpu = spuMapper.getSpuBySpuNameAndCategory3Id(spu);
        if (checkRepeatSpu != null) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("同一个三级分类下,SPU名称不能重复");
            return response;
        }
        if (spuMapper.addSpu(spu) < 1) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("SPU插入失败");
            return response;
        }
        if (spu.getSpuSaleAttrKeyList() == null || spu.getSpuSaleAttrKeyList().size() == 0){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("SPU提交成功");
            response.setData(spu.getId());
            return response;
        }
        System.out.println("开始插入SPU的新属性");
        // spuSaleAttrKey不存在更新操作,只有id,引用的spuId,saleAttrKeyId的字段,因此只有插入和删除
        List<SpuSaleAttrKey> spuSaleAttrKeyList = spu.getSpuSaleAttrKeyList();
        if (!checkSpuSaleAttrKeyList(spuSaleAttrKeyList)){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("销售属性以及属性值不能有重复且要合法");
            return response;
        }
        // 同时初始了key和value对象
        spuSaleAttrKeyList = initializeForAddSpuSaleAttrKeyList(spuSaleAttrKeyList, spu.getId());
        System.out.println("=================="+spuSaleAttrKeyList);
        if (spuSaleAttrKeyMapper.addSpuSaleAttrKeyList(spuSaleAttrKeyList) == 0) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("SPU销售属性保存失败");
            return response;
        }
        System.out.println("SPU销售属性保存成功");
        System.out.println("开始插入SPU的属性值");
        if (spuSaleAttrValueMapper.addSpuSaleAttrValueFormSpuSaleKeyList(spuSaleAttrKeyList) == 0) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("SPU销售属性值保存失败");
            return response;
        }
        response.setCode(AdminStaticData.PassCode);
        response.setMessage("SPU信息保存成功");
        response.setData(spu.getId());
        return response;
    }
    public Boolean checkSpuSaleAttrKeyList(List<SpuSaleAttrKey> spuSaleAttrKeyList) {
        System.out.println("后端不检查spuId,saleAttrKeyId是否正确且存在,是否出现重复");
        return checkRepeatSpuSaleAttrValue(spuSaleAttrKeyList);
    }
    public Boolean checkRepeatSpuSaleAttrValue(List<SpuSaleAttrKey> spuSaleAttrKeyList) {
        System.out.println("后端不检查同一销售属性下是否存在相同的属性值");
        return true;
    }
    public List<SpuSaleAttrKey> initializeForAddSpuSaleAttrKeyList(List<SpuSaleAttrKey> spuSaleAttrKeyList, Integer spuId) {
        Integer maxKeyId = spuSaleAttrKeyMapper.getMaxIdOfSpuSaleAttrKey();
        if (maxKeyId == null) {
            maxKeyId = 0;
        }
        maxKeyId++;
        Integer maxValueId = spuSaleAttrValueMapper.getMaxIdOfSpuSaleAttrValue();
        if (maxValueId == null) {
            maxValueId = 0;
        }
        for (int i = 0; i<spuSaleAttrKeyList.size(); i++) {
            spuSaleAttrKeyList.get(i).setId(maxKeyId+i);
            spuSaleAttrKeyList.get(i).setSpuId(spuId);
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrKeyList.get(i).getSpuSaleAttrValueList()) {
                maxValueId++;
                spuSaleAttrValue.setId(maxValueId);
                spuSaleAttrValue.setSpuSaleAttrKeyId(maxKeyId+i);
            }
        }
        return spuSaleAttrKeyList;
    }

    public List<SpuSaleAttrKey> getNoIdSpuSaleAttrKeyList(List<SpuSaleAttrKey> spuSaleAttrKeyList) {
        if (spuSaleAttrKeyList == null || spuSaleAttrKeyList.size() == 0){
            return spuSaleAttrKeyList;
        }
        List<SpuSaleAttrKey> noIdSpuSaleAttrKeyList = new ArrayList<SpuSaleAttrKey>();
        for (SpuSaleAttrKey spuSaleAttrKey: spuSaleAttrKeyList) {
            if (spuSaleAttrKey.getId() == null) {
                noIdSpuSaleAttrKeyList.add(spuSaleAttrKey);
            }
        }
        return noIdSpuSaleAttrKeyList;
    }

    public List<SpuSaleAttrKey> updateSpuSaleAttrKeyList(Spu spu) {
        List<SpuSaleAttrKey> spuSaleAttrKeyList = spu.getSpuSaleAttrKeyList();
        List<SpuSaleAttrKey> noIdSpuSaleAttrKeyList = getNoIdSpuSaleAttrKeyList(spuSaleAttrKeyList);
        spuSaleAttrKeyList.removeAll(noIdSpuSaleAttrKeyList);
        System.out.println("含有id的spuSaleAttrKey数组为:"+spuSaleAttrKeyList);
        System.out.println("不含有id的spuSaleAttrKey数组为:"+noIdSpuSaleAttrKeyList);

        // 判断含有id的spuSaleAttrKey数组大小,为空则删除数据库中所有key对象
        // !!!sql语句中,使用not in时,当括号中出现null时,则没有任何数据匹配上,直接返回false,not in要保证不出现null!!!
        // !!!而在in中,出现null时,会忽略掉!!!
        Integer deleteKeyNum;
        if (spuSaleAttrKeyList.size() == 0){
            deleteKeyNum = spuSaleAttrKeyMapper.deleteSpuSaleAttrKeyListBySpuId(spu);
        } else {
            // 此装配可以省略,对spuSaleAttrKeyList的操作就是对spu的此属性的操作
            spu.setSpuSaleAttrKeyList(spuSaleAttrKeyList);
            deleteKeyNum = spuSaleAttrKeyMapper.deleteSpuSaleAttrKeyListOfNoId(spu);
        }
        System.out.println("删除的spuSaleAttrKey对象的数量为:"+deleteKeyNum);
        if (noIdSpuSaleAttrKeyList.size() == 0) {
            return spuSaleAttrKeyList;
        }
        Integer maxKeyId = spuSaleAttrKeyMapper.getMaxIdOfSpuSaleAttrKey();
        if (maxKeyId == null) {
            maxKeyId = 0;
        }
        maxKeyId++;
        for(int i = 0; i<noIdSpuSaleAttrKeyList.size(); i++){
            noIdSpuSaleAttrKeyList.get(i).setId(maxKeyId + i);
            noIdSpuSaleAttrKeyList.get(i).setSpuId(spu.getId());
        }
        Integer addKeyNum = spuSaleAttrKeyMapper.addSpuSaleAttrKeyList(noIdSpuSaleAttrKeyList);
        System.out.println("新添加的spuSaleAttrKey的个数为:"+addKeyNum);
        spuSaleAttrKeyList.addAll(noIdSpuSaleAttrKeyList);
        return spuSaleAttrKeyList;
    }

    public Boolean updateSpuSaleAttrValueFromKeyList(List<SpuSaleAttrKey> spuSaleAttrKeyList) {
        List<SpuSaleAttrValue> toAddSpuSaleAttrValueList = new ArrayList<SpuSaleAttrValue>();
        List<SpuSaleAttrValue> undeleteSpuSaleAttrValueList = new ArrayList<SpuSaleAttrValue>();
        DeleteSpuSaleAttrValueSqlParam param = new DeleteSpuSaleAttrValueSqlParam();
        List<Integer> spuSaleAttrKeyIdList = new ArrayList<Integer>();
        List<Integer> undeleteSpuSaleAttrValueIdList = new ArrayList<>();
        Integer maxValueId = spuSaleAttrValueMapper.getMaxIdOfSpuSaleAttrValue();
        if (maxValueId == null) {
            maxValueId = 0;
        }

        for (SpuSaleAttrKey spuSaleAttrKey : spuSaleAttrKeyList) {
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrKey.getSpuSaleAttrValueList()) {
                if (spuSaleAttrValue.getId() == null) {
                    maxValueId++;
                    spuSaleAttrValue.setId(maxValueId);
                    spuSaleAttrValue.setSpuSaleAttrKeyId(spuSaleAttrKey.getId());
                    toAddSpuSaleAttrValueList.add(spuSaleAttrValue);
                }else {
                    spuSaleAttrValue.setSpuSaleAttrKeyId(spuSaleAttrKey.getId());
                    undeleteSpuSaleAttrValueList.add(spuSaleAttrValue);
                    undeleteSpuSaleAttrValueIdList.add(spuSaleAttrValue.getId());
                }
            }
            spuSaleAttrKeyIdList.add(spuSaleAttrKey.getId());
        }
        param.setSpuSaleAttrKeyIdList(spuSaleAttrKeyIdList);
        param.setUndeleteSpuSaleAttrValueIdList(undeleteSpuSaleAttrValueIdList);
        System.out.println("undeleteSpuSaleAttrValueList数组任何操作,只是为了打印出含id的value对象");
        System.out.println("将所有最初含id的value对象数组初始化后的数据:"+undeleteSpuSaleAttrValueList);
        System.out.println("将所有最初不含id的value对象数组初始化后的数据:"+toAddSpuSaleAttrValueList);
        try {
            // 删除操作必须在插入操作前
            Integer deleteValueNum = 0;
            if (param.getUndeleteSpuSaleAttrValueIdList().size() == 0){
                deleteValueNum = spuSaleAttrValueMapper.deleteSaleAttrValueBySpuSaleAttrKeyIdList(spuSaleAttrKeyIdList);
            }else {
                deleteValueNum = spuSaleAttrValueMapper.deleteSaleAttrValueListByIdNotInList(param);
            }
            System.out.println("删除的value对象个数为:"+deleteValueNum);
            if (toAddSpuSaleAttrValueList.size() > 0) {
                Integer addValueNum = spuSaleAttrValueMapper.addSpuSaleAttrValueList(toAddSpuSaleAttrValueList);
                System.out.println("添加的value对象个数为:"+addValueNum);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public AboutAdminResponse SpuImageFileUpload(String spuName,Integer category3Id,Integer spuId, MultipartFile file, HttpServletRequest request) {
        AboutAdminResponse response = new AboutAdminResponse();
        if (file == null) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("文件为空");
            return response;
        }
        if (spuName == null
                || spuName.trim().equals("")
                || category3Id == null
                || spuId == null){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("缺失文件信息");
            return response;
        }
        String realLocalDirPath = FileGlobalConstant.ProjectImgLocalURL + "\\img\\spu"+"\\"+category3Id+"_"+spuId+"_"+spuName;
        String temporaryDirPath=request.getServletContext().getRealPath("/img/spu/"+category3Id+"_"+spuId+"_"+spuName);
        System.out.println("本项目实际存储路径"+realLocalDirPath);
        System.out.println("服务器临时存储路径"+temporaryDirPath);
        File realFilePath = new File(realLocalDirPath);
        File temporaryPath = new File(temporaryDirPath);
        if (!realFilePath.exists()){
            realFilePath.mkdirs();
        }
        if (!temporaryPath.exists()){
            temporaryPath.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String newFilename= UUID.randomUUID()+"."+suffix;
        try {
            File realLocalFile = new File(realLocalDirPath+"\\"+newFilename);
            File temporaryFile = new File(temporaryDirPath+"\\"+newFilename);
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

            SpuImage spuImage = new SpuImage();
            spuImage.setSpuId(spuId);
            spuImage.setImageName(newFilename);
            spuImage.setImageUrl(FileGlobalConstant.ServerAddress+"/img/spu/"+category3Id+"_"+spuId+"_"+spuName+"/"+newFilename);

            // 由于前端上传图片不是一次性上传,而是每一个图片发送一次请求,因此会出现并发上传
            // 怀疑是mybatis的主键最大值搜索时,可以并行操作,这就导致可能多个程序读取的最大id相同
            // spuImage插入时,不搜索最大id,用数据库的主键自增长功能
            if (spuImageMapper.addSpuImage(spuImage) > 0) {
                response.setCode(AdminStaticData.PassCode);
                response.setMessage("图片插入成功");
                return response;
            }
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("图片数据插入失败");
        } catch (IOException e) {
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("图片上传失败");
            return response;
        }
        return response;
    }

    @Override
    public Integer deleteSpuById(Integer spuId) {
        return spuMapper.deleteSpuById(spuId);
    }

    @Override
    public List<SpuImage> getSpuImageListBySpuId(Integer spuId) {
        return spuImageMapper.getSpuImageListBySpuId(spuId);
    }

    @Override
    public List<SpuSaleAttrKey> getSpuSaleAttrList(Integer spuId) {
        return spuSaleAttrKeyMapper.getSpuSaleAttrKeyListWithValueById(spuId);
    }

    @Autowired
    public void setSpuMapper(SpuMapper spuMapper) {
        this.spuMapper = spuMapper;
    }

    @Autowired
    public void setSpuSaleAttrKeyMapper(SpuSaleAttrKeyMapper spuSaleAttrKeyMapper) {
        this.spuSaleAttrKeyMapper = spuSaleAttrKeyMapper;
    }
    @Autowired
    public void setSpuSaleAttrValueMapper(SpuSaleAttrValueMapper spuSaleAttrValueMapper) {
        this.spuSaleAttrValueMapper = spuSaleAttrValueMapper;
    }
    @Autowired
    public void setSpuImageMapper(SpuImageMapper spuImageMapper) {
        this.spuImageMapper = spuImageMapper;
    }
}
