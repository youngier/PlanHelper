package com.young.planhelper.mvp.friend.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.LinearLayout;

import com.young.planhelper.R;
import com.young.planhelper.mvp.base.BaseFragmentActivity;
import com.young.planhelper.mvp.friend.view.add.FriendAddActivity;
import com.young.planhelper.mvp.friend.view.chat.FriendChatFragment;
import com.young.planhelper.widget.NoScrollViewPager;
import com.young.planhelper.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FriendActivity extends BaseFragmentActivity {

    @BindView(R.id.ll_friend_chat_line)
    LinearLayout mChatLineLl;

    @BindView(R.id.ll_friend_person_line)
    LinearLayout mPersondLineLl;

    @BindView(R.id.nsvp_friend)
    NoScrollViewPager mFriendVp;


    @Override
    protected void initUI() {
        mToolbar.setMode(Toolbar.FRIEND);

        mToolbar.setTitle("交友");

        mToolbar.setOnRightClickListener( () -> {
            startActivity(new Intent(this, FriendAddActivity.class));
        });

        setupViewPager();
    }


    private void setupViewPager() {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new FriendChatFragment(), "信息");
        adapter.addFragment(new FriendPersonFragment(), "联系人");
        mFriendVp.setAdapter(adapter);

    }


    @Override
    public int getLayout() {
        return R.layout.activity_friend;
    }

    @Override
    public void setData(Object data) {

    }


    @OnClick(R.id.ll_friend_chat)
    void selectChat(){
        mPersondLineLl.setBackgroundColor(getResources().getColor(R.color.white));
        mChatLineLl.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
        mFriendVp.setCurrentItem(0);
    }


    @OnClick(R.id.ll_friend_person)
    void selectPerson(){
        mChatLineLl.setBackgroundColor(getResources().getColor(R.color.white));
        mPersondLineLl.setBackgroundColor(getResources().getColor(R.color.cyan_week_view_current));
        mFriendVp.setCurrentItem(1);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


}
