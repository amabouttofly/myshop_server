package com.project.shop.service.impl.base;

import com.project.shop.dao.attr.AttrMapper;
import com.project.shop.dao.attr.AttrValueMapper;
import com.project.shop.global.controller.AdminStaticData;
import com.project.shop.global.controller.ResponseCodeConstant;
import com.project.shop.pojo.po.attr.AttrKey;
import com.project.shop.pojo.po.attr.AttrValue;
import com.project.shop.pojo.res.admin.AboutAdminResponse;
import com.project.shop.service.inter.base.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class AttrServiceImpl implements AttrService {

    private AttrMapper attrMapper;
    private AttrValueMapper attrValueMapper;

    @Override
    public List<AttrKey> getAttrKeyListByCategory3Id(Integer category3Id) {
        return attrMapper.getAttrKeyListByCategory3Id(category3Id);
    }

    @Override
    public AboutAdminResponse deleteAttrInfo(Integer attrId) {
        Integer deleteAllResult = attrValueMapper.deleteAllAttrValue(attrId);
        System.out.println("删除所有属性值,个数为:"+deleteAllResult);
        AboutAdminResponse response = new AboutAdminResponse();
        if (attrMapper.deleteAttrKeyById(attrId) == 1){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("删除成功");
            return response;
        }
        response.setCode(ResponseCodeConstant.ServiceFailedCode);
        response.setMessage("删除失败");
        return response;
    }

    @Override
    public AboutAdminResponse saveAttrInfo(AttrKey attrKey) {
        // categoryId和attrKeyName不能同时重复
        // 由于attrValue的数据库表和产品表有多对多的关系,
        AboutAdminResponse response;
        if (attrKey.getAttrKeyName()==null
                || attrKey.getAttrKeyName().trim().equals("")
                || attrKey.getCategoryId()==null
                || attrKey.getCategoryLevel()==null)
        {
            response = new AboutAdminResponse();
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("属性名或属性分类不能为空");
            return response;
        }
        // attrKey属性检查完毕
        if (attrKey.getId()==null){
            System.out.println("执行attrKey的插入操作");
            response=addAttrInfo(attrKey);
        }else {
            // 更新操作时,当条件指定的行不存在时,可能返回为空,只检查id匹配,只更新attrKeyName字段
            System.out.println("执行attrKey的更新操作");
            response=updateAttrInfo(attrKey);
        }
        return response;
    }

    public AboutAdminResponse updateAttrInfo(AttrKey attrKey) {
        AboutAdminResponse response = new AboutAdminResponse();
        AttrKey checkRepeatAttrKey = attrMapper.getAttrKeyByNameAndCategory(attrKey);
        System.out.println("更新操作,id存在,为了检测categoryId和attrKeyName是否重复的测试attrKey:"+checkRepeatAttrKey);
        if (checkRepeatAttrKey!=null && !Objects.equals(checkRepeatAttrKey.getId(), attrKey.getId())){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("同一个分类下,属性名不能重复");
            return response;
        }
        // 只检查id匹配,只更新attrKeyName字段
        if (attrMapper.updateAttrKeyName(attrKey)<1){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            // 由于前面的判断,此时attrKeyName值必定不为null或空字符串
            response.setMessage("属性名的id不存在");
            return response;
        }

        if (attrKey.getAttrValueList() == null || attrKey.getAttrValueList().size()<1){
            Integer deleteAllResult = attrValueMapper.deleteAllAttrValue(attrKey.getId());
            System.out.println("删除所有的属性值,删除个数:"+deleteAllResult);
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("属性更新成功");
            return response;
        }
        List<AttrValue> attrValueList = attrKey.getAttrValueList();
        if ( !checkAttrValueList(attrValueList) ){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("属性值不能有重复且要合法");
            return response;
        }
        Integer deleteAllResult = attrValueMapper.deleteAllAttrValue(attrKey.getId());
        System.out.println("属性值检查完成,开始删除所有属性值,删除个数:"+deleteAllResult);
        attrValueList = initializeForAddAttrValueList(attrValueList, attrKey.getId());
        System.out.println("属性值插入前的初始化后数据"+attrValueList);
        if (attrValueMapper.addAttrValueList(attrValueList) > 0){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("属性名以及属性值更新成功");
            return response;
        }
        response.setCode(ResponseCodeConstant.ServiceFailedCode);
        response.setMessage("属性更新失败");
        return response;
    }
    public AboutAdminResponse addAttrInfo(AttrKey attrKey) {
        AboutAdminResponse response = new AboutAdminResponse();
        // 只有attrKeyName, categoryId, categoryName都匹配时,才会返回结果
        AttrKey checkRepeatAttrKey = attrMapper.getAttrKeyByNameAndCategory(attrKey);
        System.out.println("插入操作,id不存在,为了检测categoryId和attrKeyName是否重复的测试attrKey:"+checkRepeatAttrKey);
        if (checkRepeatAttrKey!=null){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("同一个分类下,属性名不能重复");
            return response;
        }
        // 插入后,新的id设置在attrKey属性上,由于没有匹配,且前面已经判断各个属性不为空了,因此几乎一定成功
        if (attrMapper.addAttrKey(attrKey)<1){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("属性插入失败");
            return response;
        }
        if (attrKey.getAttrValueList() == null || attrKey.getAttrValueList().size()<1){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("属性名更新成功");
            return response;
        }
        System.out.println("开始执行属性值插入");
        List<AttrValue> attrValueList = attrKey.getAttrValueList();
        if ( !checkAttrValueList(attrValueList) ){
            response.setCode(ResponseCodeConstant.ServiceFailedCode);
            response.setMessage("属性值不能有重复且要合法");
            return response;
        }
        // 插入操作后,新的id返回到attrKey上了
        attrValueList = initializeForAddAttrValueList(attrValueList, attrKey.getId());
        System.out.println("属性值插入前的初始化后数据"+attrValueList);
        if (attrValueMapper.addAttrValueList(attrValueList) > 0){
            response.setCode(AdminStaticData.PassCode);
            response.setMessage("属性名以及属性值更新成功");
            return response;
        }
        response.setCode(ResponseCodeConstant.ServiceFailedCode);
        response.setMessage("属性更新失败");
        return response;
    }

    public Boolean checkAttrValueList(List<AttrValue> attrValueList){
        System.out.println("后端不检查属性值是否合法,不为空");
        return checkRepeatAttrValueInList(attrValueList);
    }

    public Boolean checkRepeatAttrValueInList(List<AttrValue> attrValueList){
        System.out.println("后端不检查属性下的属性值是否存在相同");
        return true;
    }
    public List<AttrValue> initializeForAddAttrValueList(List<AttrValue> attrValueList, Integer attrId){
        Integer maxId=attrValueMapper.getMaxIdOfAttrValue();
        if (maxId==null){
            maxId = 0;
        }
        maxId++;
        for(int i=0; i<attrValueList.size(); i++){
            attrValueList.get(i).setId(maxId+i);
            attrValueList.get(i).setAttrId(attrId);
        }
        return attrValueList;
    }


    @Autowired
    public void setAttrMapper(AttrMapper attrMapper) {
        this.attrMapper = attrMapper;
    }
    @Autowired
    public void setAttrValueMapper(AttrValueMapper attrValueMapper) {
        this.attrValueMapper = attrValueMapper;
    }
}
