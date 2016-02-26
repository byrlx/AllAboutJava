package com.lx.git;

import com.lx.git.beans.Branch;
import com.lx.git.beans.Tag;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by douhua on 2/26/16.
 */
public class DbOperator {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/support_db";
    private static final String USER = "android";
    private static final String PASSWD = "android@coohua";

    /**
     * 更新branch信息
     *
     * @param branches TODO: 目前只做了将.git目录下解析的内容存到数据库, 数据库的更新逻辑还没有
     */
    public static void updateBranches(ArrayList<Branch> branches) {
        String exec_sql = "insert into support_db.tb_branch values(default, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
            final Connection conn2 = conn;
            Observable.from(branches)
                    .flatMap(new Func1<Branch, Observable<Branch>>() {
                        @Override
                        public Observable<Branch> call(Branch branch) {
                            return Observable.just(branch);
                        }
                    })
                    .subscribe(new Subscriber<Branch>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(Branch branch) {
                            try {
                                PreparedStatement preparedStatement = conn2.prepareStatement(exec_sql);
                                preparedStatement.setString(1, branch.getName());
                                preparedStatement.setString(2, branch.getNewestCi());
                                preparedStatement.setLong(3, branch.getUpdateTime());
                                preparedStatement.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新tag信息
     */
    public static void updateTags(ArrayList<Tag> tags) {
        String exec_sql = "insert into support_db.tb_tag values(default, ?, ?)";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWD);
            final Connection conn2 = conn;

            Observable.from(tags)
                    .flatMap(new Func1<Tag, Observable<Tag>>() {
                        @Override
                        public Observable<Tag> call(Tag tag) {
                            return Observable.just(tag);
                        }
                    })
                    .subscribe(new Action1<Tag>() {
                        @Override
                        public void call(Tag tag) {
                            PreparedStatement preparedStatement = null;
                            try {
                                preparedStatement = conn2.prepareStatement(exec_sql);
                                preparedStatement.setString(1, tag.getName());
                                preparedStatement.setString(2, tag.getCi());
                                preparedStatement.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}