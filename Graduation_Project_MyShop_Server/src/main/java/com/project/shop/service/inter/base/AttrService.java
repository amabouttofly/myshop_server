package com.project.shop.service.inter.base;

import com.project.shop.pojo.po.attr.AttrKey;
import com.project.shop.pojo.res.admin.AboutAdminResponse;

import java.util.List;

public interface AttrService {

    public List<AttrKey> getAttrKeyListByCategory3Id(Integer category3Id);

    public AboutAdminResponse saveAttrInfo(AttrKey attrKey);

    public AboutAdminResponse deleteAttrInfo(Integer attrId);
}
