package xgn.com.basesdk.base.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import me.yokeyword.fragmentation.SupportFragment;
import xgn.com.basesdk.R;
import xgn.com.basesdk.base.DialogLoadingHelper;
import xgn.com.basesdk.base.PageLoadingHelper;
import xgn.com.basesdk.base.activity.ActivityBase;
import xgn.com.basesdk.base.mvp.MvpView;
import xgn.com.basesdk.dialog.LoadingDialog;
import xgn.com.basesdk.network.ExceptionHandle;
import xgn.com.basesdk.utils.UiUtil;


/**
 * fragment基类
 * Created by yefeng on 2017/04/07.
 * Modified by yefeng
 */

public abstract class FragmentBase extends SupportFragment implements MvpView {
    protected static final String Tag = "BaseFragment";
    protected boolean loaded = false;

    private DialogLoadingHelper mLoadingHelper;
    private LoadingDialog mLoadingDialog;
    private Context mContext;
    private ImageView mImageBack;
    private TextView mTextTitle;
    private TextView mRightText;
    private ViewGroup mTitleBar;
    protected FrameLayout mContentContainer;

    /**
     * 标记当前 Fragment 是否处于可见状态
     */
    protected boolean mIsVisible;
    private PageLoadingHelper mPageLoadingHelper;
    private View mContentView;
    private SwipeRefreshLayout mSwipeRefresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setComponent();
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        mLoadingHelper = new DialogLoadingHelper(getActivity());
        mLoadingDialog = new LoadingDialog(_mActivity);
    }

    protected void setComponent() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable
                    ViewGroup container,
            @Nullable
                    Bundle savedInstanceState) {
        View view = initBaseView(inflater, container);
        initTitleBar();
        initPresenter();
        initFragment(mContentView);
        initSavadInstance(savedInstanceState);
        mContext = _mActivity;
        showContent();
        return view;
    }

    protected void setRefreshComplete() {
        if (useSwipeRefreshLayout()) {
            mSwipeRefresh.setRefreshing(false);
        }
    }

    protected SwipeRefreshLayout getRefreshLayout() {
        return mSwipeRefresh;
    }

    protected void setRefreshStart() {
        if (useSwipeRefreshLayout()) {
            mSwipeRefresh.setRefreshing(true);
        }
    }

    private View initBaseView(LayoutInflater pInflater, ViewGroup pContainer) {
        View view = pInflater
                .inflate(useSwipeRefreshLayout() ? R.layout.fragment_base_refresh_layout : R.layout.fragment_base_layout, pContainer, false);
        mContentContainer = (FrameLayout) view.findViewById(R.id.content_container);
        mTitleBar = (ViewGroup) view.findViewById(R.id.title_bar);
        mImageBack = (ImageView) view.findViewById(R.id.titlebar_back);
        mTextTitle = (TextView) view.findViewById(R.id.titlebar_title);
        mRightText = (TextView) view.findViewById(R.id.titlebar_right_text);
        mContentView = pInflater.inflate(getLayoutId(), mContentContainer, false);
        mPageLoadingHelper = new PageLoadingHelper(mContentContainer, this, mContentView);

        if (useSwipeRefreshLayout()) {
            mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
            mSwipeRefresh.setColorSchemeColors(ContextCompat.getColor(_mActivity, R.color.colorPrimary));
            mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    reLoadData();
                }
            });
        }

        return view;
    }

    /**
     * 重写此方法  下拉刷新
     */
    public void reLoadData() {
        setRefreshStart();
    }

    /**
     * 如果之类想使用下来刷新功能，需要重写 返回true
     */
    public boolean useSwipeRefreshLayout() {
        return false;
    }

    protected void initSavadInstance(Bundle savedInstanceState) {
    }

    protected void initPresenter() {
    }

    ;

    protected abstract int getLayoutId();

    public View findViewById(int id) {
        return mContentView.findViewById(id);
    }

    @Override
    public void onSupportVisible() {
        mIsVisible = true;
    }

    @Override
    public void onSupportInvisible() {
        mIsVisible = false;
    }

    protected abstract void initFragment(View view);

    /**
     * 返回要显示在标题栏中的标题
     *
     * @return 返回 null 则隐藏标题栏
     */
    public
    @StringRes
    int getTitleText() {
        return -1;
    }

    protected void onBackButtonClick() {
        onBackPressedSupport();
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    private void initTitleBar() {
        if (getActivity() instanceof ActivityBase) {
            Toolbar toolbar = ((ActivityBase) getActivity()).getToolbar();
            toolbar.setVisibility(View.GONE);
        }
        setTitle(getTitleText());
        mImageBack.setImageResource(R.drawable.icon_left_arrow);
        mImageBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClick();
            }
        });
    }

    /**
     * 该方法是给使用到地图的 Fragment 用的，因为地图中需要用到 savedInstanceState
     */
    protected void onViewInflated(View view, Bundle savedInstanceState) {
    }

    public void setTitleBarBackground(
            @ColorRes
                    int color) {
        mTitleBar.setBackgroundColor(ContextCompat.getColor(_mActivity, color));
    }

    public void hideBackButton() {
        mImageBack.setVisibility(View.GONE);
    }

    /**
     * 设置标题
     */
    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            mTextTitle.setText(title);
            mTitleBar.setVisibility(View.VISIBLE);
        } else {
            mTitleBar.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右文本
     */
    public void setRightTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            mRightText.setText(title);
            mTitleBar.setVisibility(View.VISIBLE);
        } else {
            mRightText.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右文本点击事件
     */
    public void setRightTitleClick(OnClickListener onClickListener) {
        if (mRightText != null) {
            mRightText.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置标题
     */
    public void setTitle(int titleId) {
        if (titleId == -1) {
            mTitleBar.setVisibility(View.GONE);
        } else {
            mTitleBar.setVisibility(View.VISIBLE);
            mTextTitle.setText(titleId);
        }
    }

    public boolean checkNetworkState() {
        ConnectivityManager cm = (ConnectivityManager) _mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Toast 提示
     */
    @Override
    public void showToast(int resid) {
        UiUtil.showToast(mContext, resid);
    }

    /**
     * Toast 提示
     */
    @Override
    public void showToast(int resid, int duration) {
        UiUtil.showToast(mContext, resid);
    }

    /**
     * Toast 提示
     */
    @Override
    public void showToast(CharSequence message) {
        if (!TextUtils.isEmpty(message)) {
            UiUtil.showToast(mContext, message.toString());
        }
    }

    /**
     * Toast 提示
     */
    @Override
    public void showToast(CharSequence message, int duration) {
        if (!TextUtils.isEmpty(message)) {
            UiUtil.showToast(mContext, message.toString());
        }
    }

    @Override
    public void showMsg(int title, int des) {
        UiUtil.showToast(mContext, title, des);
    }

    /**
     * 显示等待框
     * 默认显示"加载中..."
     */
    @Override
    public void showWaiting() {
        mLoadingHelper.showWaiting(null, getActivity());
    }

    /**
     * 默认显示"加载中..."
     */
    @Override
    public void showWaiting(boolean instantShow) {
        mLoadingHelper.showWaiting(instantShow, getActivity());
    }

    /**
     * 显示等待框
     * 默认显示"加载中..."
     */
    @Override
    public void showWaiting(int strId) {
        String message = getString(strId);
        mLoadingHelper.showWaiting(message, getActivity());
    }

    /**
     * 显示等待框
     */
    @Override
    public void showWaiting(int strId, boolean isCancelable) {
        isCancelable = true;
        //        String message = getString(strId);
        //        mLoadingHelper.showWaiting(message, isCancelable, getActivity());
        mLoadingDialog.setMessage(strId);
        mLoadingDialog.setCancelable(isCancelable);
        mLoadingDialog.show();
    }

    /**
     * 显示等待框
     * 默认显示"加载中..."
     */
    @Override
    public void showWaiting(String message) {
        mLoadingHelper.showWaiting(message, getActivity());
    }

    /**
     * 显示等待框
     * 默认显示"加载中..."
     */
    @Override
    public void showWaiting(String message, boolean isCancelable) {
        mLoadingHelper.showWaiting(message, isCancelable, getActivity());
    }

    /**
     * 如果使用了下拉刷新，重新加载数据成功后需要调此方法
     */
    public void showContent() {
        mPageLoadingHelper.showContent();
        setRefreshComplete();
    }

    /**
     * 隐藏等待框
     * 顺便隐藏refresh
     */
    @Override
    public void stopWaiting() {
        mLoadingHelper.stopWaiting();
        mLoadingDialog.dismiss();
    }

    /**
     * 显示或隐藏输入法
     */
    protected void showKeyboard(boolean isShow) {
        if (getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (isShow) {
                if (getActivity().getCurrentFocus() == null) {
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                } else {
                    imm.showSoftInput(getActivity().getCurrentFocus(), 0);
                }
            } else {
                if (getActivity().getCurrentFocus() != null) {
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }

    }

    @Override
    public void onExceptionHandle(ExceptionHandle.ResponseThrowable restError) {

    }

    @Override
    public void showErrorView() {
        setRefreshComplete();
        mPageLoadingHelper.showErrorView();
    }

    @Override
    public void showErrorView(ExceptionHandle.ResponseThrowable throwable) {
        setRefreshComplete();
        mPageLoadingHelper.showErrorView(throwable);
    }

    @Override
    public void showErrorView(
            @Nullable
                    String pErrorMes,
            @DrawableRes
                    int pErrorIconRes) {
        setRefreshComplete();
        mPageLoadingHelper.showErrorView(pErrorMes, pErrorIconRes);
    }

    @Override
    public void showErrorView(View pview) {
        setRefreshComplete();
        mPageLoadingHelper.showErrorView(pview);
    }

    @Override
    public void showPageInprossView() {
        mPageLoadingHelper.showInPageProgressView();
    }

    @Override
    public void showEmptyView(
            @DrawableRes
                    int pEmptyIconRes,
            @Nullable
                    String pEpmtyMes) {
        setRefreshComplete();
        mPageLoadingHelper.showEmptyView(pEmptyIconRes, pEpmtyMes);
    }

    @Override
    public void showEmptyView() {
        setRefreshComplete();
        mPageLoadingHelper.showEmptyView();
    }
}

