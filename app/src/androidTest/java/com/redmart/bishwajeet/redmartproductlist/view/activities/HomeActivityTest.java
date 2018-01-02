package com.redmart.bishwajeet.redmartproductlist.view.activities;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.redmart.bishwajeet.redmartproductlist.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rajan on 12/31/2017.
 */
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityActivityTestRule=new ActivityTestRule<HomeActivity>(HomeActivity.class);

    HomeActivity mHomeActivity=null;
    @Before
    public void setUp() throws Exception {
        mHomeActivity=homeActivityActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch() {
        View view =mHomeActivity.findViewById(R.id.rv_products);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mHomeActivity=null;
    }

}