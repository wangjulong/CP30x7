package com.wangjulong;

import com.wangjulong.db.DatabaseAccess;
import com.wangjulong.db.InitDataOkhttp;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    /**
     * 从网络获取开奖号码
     *
     * @param actionEvent 按钮事件
     */
    public void getLotteryFromWeb(ActionEvent actionEvent) throws IOException, SQLException {
        InitDataOkhttp initDataOkhttp = new InitDataOkhttp();

        int[][] temp = initDataOkhttp.initData();
        for (int[] aTemp : temp) {
            System.out.print(aTemp[0] + " ");
            System.out.print(aTemp[1] + " ");
            System.out.print(aTemp[2] + " ");
            System.out.print(aTemp[3] + " ");
            System.out.print(aTemp[4] + " ");
            System.out.print(aTemp[5] + " ");
            System.out.print(aTemp[6] + " ");
            System.out.print(aTemp[7] + " ");
            System.out.println(aTemp[8]);
        }
    }

    public void dataOnly(ActionEvent actionEvent) {
        DatabaseAccess dataOnly = new DatabaseAccess();
        try {
            int[][] temp = dataOnly.getAllNumber();
            for (int[] aTemp : temp) {
                System.out.print(aTemp[0] + " ");
                System.out.print(aTemp[1] + " ");
                System.out.print(aTemp[2] + " ");
                System.out.print(aTemp[3] + " ");
                System.out.print(aTemp[4] + " ");
                System.out.print(aTemp[5] + " ");
                System.out.print(aTemp[6] + " ");
                System.out.print(aTemp[7] + " ");
                System.out.println(aTemp[8]);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }
}
