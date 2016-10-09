package com.robot.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TabBeanUtils {
    private static List<TabBean> tabBeanList = new ArrayList<>();

    private TabBeanUtils() {
    }

    public static List<TabBean> getTabBeanList() {
        if (tabBeanList.size() == 0) {
            tabBeanList.add(new TabBean(0, "每日推荐"));
            tabBeanList.add(new TabBean(1, "职场指南"));
            tabBeanList.add(new TabBean(2, "经典语录"));
        }

        return tabBeanList;
    }

    public static TabBean getTabBean(int index) {
        return getTabBeanList().get(index);
    }

    public static int getTabBeanId(int index) {
        return getTabBeanList().get(index).getId();
    }

    public static String getTabBeanTitle(int index) {
        return getTabBeanList().get(index).getTitle();
    }

    public static class TabBean implements Parcelable{
        private int id;
        private String title;

        public TabBean(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
        }

        protected TabBean(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
        }

        public static final Creator<TabBean> CREATOR = new Creator<TabBean>() {
            public TabBean createFromParcel(Parcel source) {
                return new TabBean(source);
            }

            public TabBean[] newArray(int size) {
                return new TabBean[size];
            }
        };
    }
}
