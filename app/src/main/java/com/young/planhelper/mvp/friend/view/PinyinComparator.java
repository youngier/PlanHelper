package com.young.planhelper.mvp.friend.view;

import com.young.planhelper.mvp.login.model.bean.User;

import java.util.Comparator;

/**
 *
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<User> {

    public int compare(User o1, User o2) {
        if (o1.getSortLetters().equals("@")
                || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }

}
