package clear;

import com.project.shop.global.controller.FileGlobalConstant;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class ClearUselessSpuImage {
    public static void main(String[] args) {
        SpuDao spuDao = new SpuDao();
        List<SimpleSpu> simpleSpuList = spuDao.queryAllSpu();
        System.out.println(simpleSpuList);
    }

    private final String filePath = FileGlobalConstant.ProjectImgLocalURL+"\\img\\spu";

    public void startClear(){
        SpuDao spuDao = new SpuDao();
        List<SimpleSpu> simpleSpuList = spuDao.queryAllSpu();
    }



    public String[] getAllFilenames(File file){
        if (!file.isDirectory()){
            return null;
        }
        return file.list();
    }
}
