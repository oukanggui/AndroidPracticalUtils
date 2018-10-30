package com.baymax.utils.fragment;

import android.view.View;
import android.widget.TextView;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.JsonUtil;
import com.baymax.utilslib.ToastUtil;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/30
 * 描述：JsonUtil操作演示Fragment
 */

public class JsonUtilFragment extends BaseContentFragment {

    @BindView(R.id.tv_object_showjson)
    TextView tvShowObjectJson;
    @BindView(R.id.tv_list_showjson)
    TextView tvShowListJson;

    private User mUser;
    private List<User> userList;

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_json;
    }

    @Override
    protected void initData() {
        super.initData();
        mUser = new User("oukanggui", "https://github.com/oukanggui");
        userList = new ArrayList<>();
        userList.add(mUser);
        userList.add(new User("baymax", "https://github.com/oukanggui"));
    }

    @OnClick({R.id.bt_object_tojson, R.id.bt_list_tojson, R.id.bt_object_fromjson, R.id.bt_list_fromjson})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_object_tojson:
                tvShowObjectJson.setVisibility(View.VISIBLE);
                tvShowObjectJson.setText(JsonUtil.toJson(mUser));
                break;
            case R.id.bt_list_tojson:
                tvShowListJson.setVisibility(View.VISIBLE);
                tvShowListJson.setText(JsonUtil.toJson(userList));
                break;
            case R.id.bt_object_fromjson:
                User user = JsonUtil.parseJson(tvShowObjectJson.getText().toString(), User.class);
                if (user != null) {
                    ToastUtil.showToast(mActivity, user.toString());
                } else {
                    ToastUtil.showToast(mActivity, "转换有误！");
                }
                break;
            case R.id.bt_list_fromjson:
                Type listType = new TypeToken<List<User>>() {
                }.getType();
                List<User> users = JsonUtil.parseJson(tvShowListJson.getText().toString(), listType);
                if (users != null) {
                    ToastUtil.showToast(mActivity, users.toString());
                } else {
                    ToastUtil.showToast(mActivity, "转换有误！");
                }
                break;
            default:
                break;
        }

    }

    private static class User {
        private String name;
        private String githubUrl;

        public User(String name, String githubUrl) {
            this.name = name;
            this.githubUrl = githubUrl;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", githubUrl='" + githubUrl + '\'' +
                    '}';
        }
    }
}
