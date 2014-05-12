package com.espian.showcaseview.targets;

import android.app.Activity;
import android.graphics.Point;
import android.view.ViewParent;

import com.espian.showcaseview.actionbar.ActionBarViewWrapper;
import com.espian.showcaseview.actionbar.reflection.BaseReflector;

public class ActionItemTarget implements Target {

    private final Activity mActivity;
    private final int mItemId;

    ActionBarViewWrapper mActionBarWrapper;

    public ActionItemTarget(Activity activity, int itemId) {
        mActivity = activity;
        mItemId = itemId;
    }

    @Override
    public Point getPoint() {
        setUp();
        ViewTarget viewTarget = new ViewTarget(mActionBarWrapper.getActionItem(mItemId));
        Point point = viewTarget.getPoint();
    	if (point == null) {
    		point = getAlternativePoint();
    	}
        return point;
    }

    private Point getAlternativePoint() {
    	ViewTarget viewTarget = new ViewTarget(mActionBarWrapper.getOverflowView());
    	Point point = viewTarget.getPoint();
    	if (point == null) {
    		return new Point(0, 0);
    	}
		return point;
	}

	protected void setUp() {
        BaseReflector reflector = BaseReflector.getReflectorForActivity(mActivity);
        ViewParent p = reflector.getActionBarView(); //ActionBarView
        mActionBarWrapper = new ActionBarViewWrapper(p);
    }

}
