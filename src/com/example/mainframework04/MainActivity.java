package com.example.mainframework04;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();

	/**
	 * �������LinearLayout
	 */
	private LinearLayout mTabLiaotian;
	private LinearLayout mTabFaxian;
	private LinearLayout mTabTongxunlun;

	/**
	 * ���������TextView
	 */
	private TextView mLiaotian;
	private TextView mFaxian;
	private TextView mTongxunlu;

	/**
	 * �ֱ�Ϊÿ��TabIndicator����һ��BadgeView
	 */
	private BadgeView mBadgeViewforLiaotian;
	private BadgeView mBadgeViewforFaxian;
	private BadgeView mBadgeViewforTongxunlu;

	/**
	 * Tab���Ǹ�����
	 */
	private ImageView mTabLine;
	/**
	 * ViewPager�ĵ�ǰѡ��ҳ
	 */
	private int currentIndex;
	/**
	 * ��Ļ�Ŀ��
	 */
	private int screenWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_main);
		Intent intent=new Intent(this,SMServer.class);
		startService(intent);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		initView();
		initTabLine();
		/**
		 * ���ö��������ǩҳ����¼�
		 */
		mTabLiaotian.setOnClickListener(this);
		mTabFaxian.setOnClickListener(this);
		mTabTongxunlun.setOnClickListener(this);

		/**
		 * ��ʼ��Adapter
		 */
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mFragments.get(arg0);
			}
		};
		/**
		 * ��ֹviewpager����
		 */
		// mViewPager.setOnTouchListener(new OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		// switch (event.getAction()) {
		// case MotionEvent.ACTION_MOVE:
		// mViewPager.requestDisallowInterceptTouchEvent(true);
		// break;
		// case MotionEvent.ACTION_UP:
		// case MotionEvent.ACTION_CANCEL:
		// mViewPager.requestDisallowInterceptTouchEvent(false);
		// break;
		// }
		// return true;
		// }
		// });
		mViewPager.setCurrentItem(1);
		mViewPager.setAdapter(mAdapter);
		

		/**
		 * ���ü���
		 */
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// ��������TextView��������ɫ
				resetTextView();
				switch (position) {
				case 0:
					mTabLiaotian.removeView(mBadgeViewforLiaotian);
					//mBadgeViewforLiaotian.setBadgeCount(5);
					mTabLiaotian.addView(mBadgeViewforLiaotian);
					mLiaotian.setTextColor(getResources().getColor(
							R.color.green));
					break;
				case 1:
					mFaxian.setTextColor(getResources().getColor(R.color.green));
					mTabFaxian.removeView(mBadgeViewforFaxian);
					//mBadgeViewforFaxian.setBadgeCount(15);
					mTabFaxian.addView(mBadgeViewforFaxian);
					break;
				case 2:
					mTongxunlu.setTextColor(getResources().getColor(
							R.color.green));

					break;
				}

				currentIndex = position;
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				/**
				 * ����position��currentIndex�ж��û��Ĳ�������һҳ����һҳ����
				 * Ȼ��ı���positionOffset��̬�ı�TabLine��leftMargin
				 */
				/**
				 * currentIndex:��ǰ�� ;positionOffsetPixels: λ��ƫ������;
				 * positionOffset:λ��ƫ��
				 */
				System.out.println("\n" + "currentIndex:" + currentIndex + "\n"
						+ "position:" + position + "\n" + "positionOffset:"
						+ positionOffset + "\n" + "positionOffsetPixels:"
						+ positionOffsetPixels + "\n");
				if (currentIndex == 0 && position == 0)// 0->1
				{

					LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
							.getLayoutParams();
					lp.leftMargin = (int) (positionOffset
							* (screenWidth * 1.0 / 3) + currentIndex
							* (screenWidth / 3));
					mTabLine.setLayoutParams(lp);// ��λ��0ƽ�Ƶ�λ��1

				} else if (currentIndex == 1 && position == 0) // 1->0
				{
					LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
							.getLayoutParams();
					lp.leftMargin = (int) (-(1 - positionOffset)
							* (screenWidth * 1.0 / 3) + currentIndex
							* (screenWidth / 3));
					mTabLine.setLayoutParams(lp);// ��λ��1ƽ�Ƶ�λ��0

				} else if (currentIndex == 1 && position == 1) // 1->2
				{
					LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
							.getLayoutParams();
					lp.leftMargin = (int) (positionOffset
							* (screenWidth * 1.0 / 3) + currentIndex
							* (screenWidth / 3));
					mTabLine.setLayoutParams(lp);// ��λ��1ƽ�Ƶ�λ��2
				} else if (currentIndex == 2 && position == 1) // 2->1
				{
					LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
							.getLayoutParams();
					lp.leftMargin = (int) (-(1 - positionOffset)
							* (screenWidth * 1.0 / 3) + currentIndex
							* (screenWidth / 3));
					mTabLine.setLayoutParams(lp);// ��λ��2ƽ�Ƶ�λ��1

				}

			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

	}

	/**
	 * �����Ļ�Ŀ�ȣ���ʼ�����ߵĿ��
	 */
	private void initTabLine() {
		mTabLine = (ImageView) findViewById(R.id.id_tab_line);
		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindow().getWindowManager().getDefaultDisplay()
				.getMetrics(outMetrics);
		screenWidth = outMetrics.widthPixels;
		LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
				.getLayoutParams();
		lp.width = screenWidth / 3;
		mTabLine.setLayoutParams(lp);
	}

	/**
	 * ������ɫ
	 */
	protected void resetTextView() {
		mLiaotian.setTextColor(getResources().getColor(R.color.black));
		mFaxian.setTextColor(getResources().getColor(R.color.black));
		mTongxunlu.setTextColor(getResources().getColor(R.color.black));
	}

	/**
	 * ��ʼ���ؼ�����ʼ��Fragment
	 */
	private void initView() {

		mTabLiaotian = (LinearLayout) findViewById(R.id.id_tab_liaotian_ly);
		mTabFaxian = (LinearLayout) findViewById(R.id.id_tab_faxian_ly);
		mTabTongxunlun = (LinearLayout) findViewById(R.id.id_tab_tongxunlu_ly);

		mLiaotian = (TextView) findViewById(R.id.id_liaotian);
		mFaxian = (TextView) findViewById(R.id.id_faxian);
		mTongxunlu = (TextView) findViewById(R.id.id_tongxunlu);

		MainTab01 tab01 = new MainTab01();
		MainTab02 tab02 = new MainTab02();
		MainTab03 tab03 = new MainTab03();
		mFragments.add(tab01);
		mFragments.add(tab02);
		mFragments.add(tab03);

		mBadgeViewforFaxian = new BadgeView(this);
		mBadgeViewforLiaotian = new BadgeView(this);
		mBadgeViewforTongxunlu = new BadgeView(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.id_tab_liaotian_ly:
			mViewPager.setCurrentItem(0);
			break;
		case R.id.id_tab_faxian_ly:
			mViewPager.setCurrentItem(1);
			break;
		case R.id.id_tab_tongxunlu_ly:
			mViewPager.setCurrentItem(2);
			break;

		default:
			break;
		}
	}
}
