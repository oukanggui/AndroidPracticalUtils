package com.baymax.utils.fragment;

import android.view.View;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.TimeUtil;
import com.baymax.utilslib.ToastUtil;

import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/30
 * 描述：TimeUtil操作说明工具类
 */

public class TimeUtilFragment extends BaseContentFragment {

    private static final String strStartDate = "2018-10-30 21:00:00";
    private static final String strEndDate = "2018-10-31 21:00:00";

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_time;
    }

    @OnClick({R.id.bt_get_systime, R.id.bt_same_day, R.id.bt_gap_seconds, R.id.bt_gap_minutes,
            R.id.bt_gap_hours, R.id.bt_gap_days})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_systime:
                ToastUtil.showToast(mActivity, TimeUtil.getSystemCurrentTime());
                break;
            case R.id.bt_same_day:
                if (TimeUtil.isSameDay(strStartDate, strEndDate)) {
                    ToastUtil.showToast(mActivity, "同一天");
                } else {
                    ToastUtil.showToast(mActivity, "不是同一天");
                }
                break;
            case R.id.bt_gap_seconds:
                ToastUtil.showToast(mActivity, "相差" + TimeUtil.twoDateGapSeconds(strStartDate, strEndDate) + "秒");
                break;
            case R.id.bt_gap_minutes:
                ToastUtil.showToast(mActivity, "相差" + TimeUtil.twoDateGapMinutes(strStartDate, strEndDate) + "分钟");
                break;
            case R.id.bt_gap_hours:
                ToastUtil.showToast(mActivity, "相差" + TimeUtil.twoDateGapHours(strStartDate, strEndDate) + "小时");
                break;
            case R.id.bt_gap_days:
                ToastUtil.showToast(mActivity, "相差" + TimeUtil.twoDateGapDays(strStartDate, strEndDate) + "天");
                break;
            default:
                break;
        }

    }
}
