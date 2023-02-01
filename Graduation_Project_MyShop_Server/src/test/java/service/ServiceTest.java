package service;

import com.project.shop.pojo.po.spu.SpuImage;
import com.project.shop.pojo.po.user.Review;
import com.project.shop.pojo.req.product.SearchSkuRequest;
import com.project.shop.pojo.req.userAbout.SkuReviewListRequest;
import com.project.shop.pojo.res.admin.AboutUserResponse;
import com.project.shop.service.inter.base.*;
import com.project.shop.service.inter.role.AdminService;
import com.project.shop.service.inter.user.CartService;
import com.project.shop.service.inter.user.ReviewService;
import com.project.shop.service.inter.user.TradeService;
import com.project.shop.service.inter.visitor.SkuVisitorService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.*;

public class ServiceTest {

    ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testTrademark(){
        TrademarkService trademarkService=applicationContext.getBean(TrademarkService.class);
        System.out.println(trademarkService.getTrademarkList());
//        Trademark trademark = new Trademark();
//        trademark.setTmName("55");
//        trademark.setLogoUrl("12");
//        trademark.setId(7);
//        System.out.println(trademarkService.updateTrademarkById(trademark));
    }
    @Test
    public void testAdmin(){
        AdminService adminService = applicationContext.getBean(AdminService.class);
        System.out.println(adminService.queryAdminByUsername(null,null));
    }
    @Test
    public void testCategory(){
        CategoryService categoryService = applicationContext.getBean(CategoryService.class);
        SearchSkuRequest searchSkuRequest = new SearchSkuRequest();
        searchSkuRequest.setCategory1Id(1);
        searchSkuRequest.setCategory2Id(10);
        searchSkuRequest.setCategory3Id(1);
        System.out.println(categoryService.getCategory3IdByAllLevel(searchSkuRequest));
    }
    @Test
    public void testAttr(){
        AttrService attrService = applicationContext.getBean(AttrService.class);
//        AttrKey attrKey=new AttrKey();
//        attrKey.setId(7);
//        attrKey.setAttrKeyName("拉拉");
//        attrKey.setCategoryId(99);
//        attrKey.setCategoryLevel(88);
//        System.out.println(attrService.saveAttrInfo(attrKey));
        //System.out.println(attrService.getMaxIdOfAttrValue());
    }

    @Test
    public void testSpu(){
        SpuService spuService = applicationContext.getBean(SpuService.class);
//        Spu spu = new Spu();
//        spu.setId(8);
//        spu.setSpuName("测试22");
//        spu.setDescription("12322");
//        spu.setCategory3Id(2);
//        spu.setTmId(4);
//        System.out.println("---------------------------------");
        System.out.println(spuService.getSpuSaleAttrList(1));
//        System.out.println("---------------------------------");
    }

    @Test
    public void testSpuImage() {
        SpuService spuService = applicationContext.getBean(SpuService.class);
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(6);
        spuImage.setImageUrl("测试");
        spuImage.setImageName("456");
//        System.out.println(spuService.addSpuImage(spuImage));
        System.out.println(spuImage);
    }

    @Test
    public void testSku(){
        SkuService skuService = applicationContext.getBean(SkuService.class);
        System.out.println(skuService.getSkuBySkuId(1));
    }

    @Test
    public void testSkuVisitor(){
        SkuVisitorService skuVisitorService = applicationContext.getBean(SkuVisitorService.class);
        skuVisitorService.getSkuDetailInfo(100);
    }

    @Test
    public void testCartService(){
        CartService cartService = applicationContext.getBean(CartService.class);
        System.out.println(cartService.addCartBySkuIdAndNum(2,5,1));
    }

    @Test
    public void testTradeService(){
        TradeService tradeService = applicationContext.getBean(TradeService.class);
        System.out.println(tradeService.getOrderDetail(1));
    }

    @Test
    public void testReviewService(){
        ReviewService reviewService = applicationContext.getBean(ReviewService.class);
        SkuReviewListRequest request = new SkuReviewListRequest();
        // request.setReviewRateList(new ArrayList<Integer>());
        AboutUserResponse skuReviewList = reviewService.getSkuReviewList(request);

    }

    @Test
    public void testBaseReviewService(){
        BaseReviewService baseReviewService = applicationContext.getBean(BaseReviewService.class);
    }

    @Test
    public void testMy(){
//        List<Spu> spuList = null;
//        for (Spu i: spuList){
//            System.out.println(i);
//        }

//        DateFormat mediumFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,DateFormat.MEDIUM);
//        Date date = new Date();
//        System.out.println(mediumFormat.format(date));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DATE,1);
//        System.out.println(mediumFormat.format(calendar.getTime()));

//        String warring = "这是一个正在测试中的实验系统,可能会造成邮箱误发,对此深表歉意";
//        String enWarring = "This is an experimental system under test, which may cause the email to be sent by mistake. We apologize for this";
//        String mailMessage = warring + "\n" + enWarring + "\n" + "\n"+  "正在进行账号注册,绑定当前邮箱,验证码为:"+13312;
//        System.out.println(mailMessage);

        double one = 5.26419;
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        String str = format.format(one);
        System.out.println(str);
    }
}
