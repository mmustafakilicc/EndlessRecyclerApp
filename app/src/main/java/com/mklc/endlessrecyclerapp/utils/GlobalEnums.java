package com.mklc.endlessrecyclerapp.utils;

public class GlobalEnums {

    public enum AdapterViewType {
        VIEW_TYPE_DATA(1),
        VIEW_TYPE_LOAD(2);

        private final int viewType;

        AdapterViewType(int viewType) {
            this.viewType = viewType;
        }

        public int getViewType() {
            return viewType;
        }
    }
}
