package com.young.planhelper.mvp.friend.view;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.view.BaseFragment;
import com.young.planhelper.mvp.friend.presenter.FriendPresenter;
import com.young.planhelper.mvp.friend.presenter.IFriendPresenter;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.widget.ClearEditText;
import com.young.planhelper.widget.SlideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/2/16  23:08
 */


public class FriendPersonFragment extends BaseFragment {

    @BindView(R.id.lv_friend_person)
    ListView mPersonLv;

    @BindView(R.id.slidrbar)
    SlideBar slideBar;

    @BindView(R.id.dialog)
    TextView dialog;

    private SortGroupMemberAdapter adapter;

    @BindView(R.id.filter_edit)
    ClearEditText mClearEditText;

    @BindView(R.id.title_layout)
    LinearLayout titleLayout;

    @BindView(R.id.title_layout_catalog)
    TextView title;

    @BindView(R.id.title_layout_no_friends)
    TextView tvNofriends;

    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<User> SourceDateList = new ArrayList<>();

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    private IFriendPresenter presenter;


    @Override
    protected void setData() {

    }

    @Override
    protected void initUI() {

        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        // 设置右侧触摸监听
        slideBar.setOnTouchingLetterChangedListener( s -> {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mPersonLv.setSelection(position);
                }
        });

        adapter = new SortGroupMemberAdapter(getActivity(), SourceDateList);

        mPersonLv.setAdapter(adapter);

        mPersonLv.setOnItemClickListener( (parent, view, position, id) ->{
            Intent intent = new Intent(getActivity(), FriendDetailActivity.class);
            intent.putExtra("user", SourceDateList.get(position));
            startActivity(intent);
        });

        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 这个时候不需要挤压效果 就把他隐藏掉
                titleLayout.setVisibility(View.GONE);
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        presenter = new FriendPresenter(this, getActivity());

        presenter.getAllFriend(data -> setData(data));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend_person;
    }

    @Override
    public void setData(Object data) {
        Observable<List<User>> userList = (Observable<List<User>>) data;
        userList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {

                    @Override
                    public void onCompleted() {
                        Log.i("way", "onCompleted");
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("way", "onError" + e.toString());
                        hideProgress();
                    }

                    @Override
                    public void onNext(List<User> userList) {
                        List<String> content = new ArrayList<String>();
                        Log.i("way", "好友列表有" + userList.size() + "个");
                        hideProgress();
                        if( userList != null && userList.size() > 0 ){
                            SourceDateList = filledData(userList);

                            adapter.setData(SourceDateList);

                            adapter.notifyDataSetChanged();

                            mPersonLv.setOnScrollListener(new AbsListView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(AbsListView view, int scrollState) {
                                }

                                @Override
                                public void onScroll(AbsListView view, int firstVisibleItem,
                                                     int visibleItemCount, int totalItemCount) {

                                    if( SourceDateList.size() > 1 ) {

                                        int section = getSectionForPosition(firstVisibleItem);
                                        int nextSection = getSectionForPosition(firstVisibleItem + 1);
                                        int nextSecPosition = getPositionForSection(+nextSection);
                                        if (firstVisibleItem != lastFirstVisibleItem) {
                                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                                    .getLayoutParams();
                                            params.topMargin = 0;
                                            titleLayout.setLayoutParams(params);
                                            title.setText(SourceDateList.get(
                                                    getPositionForSection(section)).getSortLetters());
                                        }
                                        if (nextSecPosition == firstVisibleItem + 1) {
                                            View childView = view.getChildAt(0);
                                            if (childView != null) {
                                                int titleHeight = titleLayout.getHeight();
                                                int bottom = childView.getBottom();
                                                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                                        .getLayoutParams();
                                                if (bottom < titleHeight) {
                                                    float pushedDistance = bottom - titleHeight;
                                                    params.topMargin = (int) pushedDistance;
                                                    titleLayout.setLayoutParams(params);
                                                } else {
                                                    if (params.topMargin != 0) {
                                                        params.topMargin = 0;
                                                        titleLayout.setLayoutParams(params);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    lastFirstVisibleItem = firstVisibleItem;
                                }
                            });

                            // 根据a-z进行排序源数据
                            Collections.sort(SourceDateList, pinyinComparator);

                        }
                    }
                });
    }


    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<User> filledData(List<User> date) {
        List<User> mSortList = new ArrayList<User>();

        for (int i = 0; i < date.size(); i++) {
            User sortModel = new User();
            sortModel.setUserId(date.get(i).getUserId());
            sortModel.setAccount(date.get(i).getAccount());
            sortModel.setIconUrl(date.get(i).getIconUrl());
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getAccount());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<User> filterDateList = new ArrayList<User>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
            tvNofriends.setVisibility(View.GONE);
        } else {
            filterDateList.clear();

            for (User sortModel : SourceDateList) {
                String name = sortModel.getAccount();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
        if (filterDateList.size() == 0) {
            tvNofriends.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return SourceDateList.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < SourceDateList.size(); i++) {
            String sortStr = SourceDateList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

}
