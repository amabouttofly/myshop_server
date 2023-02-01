package clear;

import clear.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpuDao {
    private Connection connection;
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<SimpleSpu> queryAllSpu() {
        connection = DBUtil.getConnection();
        sql = "select * from spu_list";
        List<SimpleSpu> simpleSpuList = Collections.synchronizedList(new ArrayList<SimpleSpu>());
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SimpleSpu simpleSpu = new SimpleSpu();
                simpleSpu.setId(resultSet.getInt("id"));
                simpleSpu.setCategory3Id(resultSet.getInt("category3Id"));
                simpleSpu.setSpuName(resultSet.getString("spuName"));
                simpleSpuList.add(simpleSpu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection();
        }
        return simpleSpuList;
    }
}
