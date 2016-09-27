package com.wangjulong.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库访问类
 * 使用 Ormlite 访问数据库
 * Created by Administrator on 2016/9/21.
 */
public class DatabaseAccess {

    // 数据库连接字符串
    private final static String DATABASE_URL = "jdbc:sqlite:cp7.db";

    // DAO - Data-Access-Object
    private Dao<LotteryNumber, Integer> lotteryDao;

    // 添加从网络获取的数据到数据库 cp7.lotteryNumbers
    void addToLottery(String str) throws IOException, SQLException {
        ConnectionSource connectionSource = null;

        try {
            // 数据库连接实例化
            connectionSource = new JdbcConnectionSource(DATABASE_URL);

            // DAO 类实例化
            lotteryDao = DaoManager.createDao(connectionSource, LotteryNumber.class);

            // 创建数据库文件
            TableUtils.createTableIfNotExists(connectionSource, LotteryNumber.class);

            // 清空数据库
            TableUtils.clearTable(connectionSource, LotteryNumber.class);

            // 保存数据库数据
            String[] arr0 = str.split("data-period=\"");

            // 临时变量
            int title, n1, n2, n3, n4, n5, n6, n7;
            int serial = 0;

            // 循环字符串数组
            for (String abc : arr0) {
                // 正则表达式匹配开始的8个字符是否是数字，更新数据库
                // 2016011" data-award="05 07 10 13 21 25 27:09">
                if (abc.substring(0, 7).matches("\\d{7}")) {
                    title = Integer.parseInt(abc.substring(0, 7));
                    n1 = Integer.parseInt(abc.substring(21, 23));
                    n2 = Integer.parseInt(abc.substring(24, 26));
                    n3 = Integer.parseInt(abc.substring(27, 29));
                    n4 = Integer.parseInt(abc.substring(30, 32));
                    n5 = Integer.parseInt(abc.substring(33, 35));
                    n6 = Integer.parseInt(abc.substring(36, 38));
                    n7 = Integer.parseInt(abc.substring(39, 41));
                    serial++;

                    LotteryNumber lotteryNumber = new LotteryNumber(serial, title, n1, n2, n3, n4, n5, n6, n7);
                    lotteryDao.create(lotteryNumber);
                }
            }


        } finally {
            // 关闭数据库连接
            if (connectionSource != null) {
                connectionSource.close();
            }
        }
    }

    // 返回数据库数据
    public int[][] getAllNumber() throws SQLException, IOException {

        ConnectionSource connectionSource = null;

        try {
            // 数据库连接实例化
            connectionSource = new JdbcConnectionSource(DATABASE_URL);

            // DAO 类实例化
            lotteryDao = DaoManager.createDao(connectionSource, LotteryNumber.class);

            // 返回数据库数据
            List<LotteryNumber> list = lotteryDao.queryForAll();
            int[][] temp = new int[list.size()][9];
            for (int i = 0; i < list.size(); i++) {
                temp[i][0] = list.get(i).getSerial();
                temp[i][1] = list.get(i).getTitle();
                temp[i][2] = list.get(i).getN1();
                temp[i][3] = list.get(i).getN2();
                temp[i][4] = list.get(i).getN3();
                temp[i][6] = list.get(i).getN4();
                temp[i][7] = list.get(i).getN5();
                temp[i][8] = list.get(i).getN6();
                temp[i][5] = list.get(i).getN7();
            }
            return temp;

        } finally {
            // 关闭数据库连接
            if (connectionSource != null) {
                connectionSource.close();
            }
        }


    }

}
