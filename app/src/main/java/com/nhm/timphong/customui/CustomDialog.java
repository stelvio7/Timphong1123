package com.nhm.timphong.customui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.nhm.timphong.R;
import com.nhm.timphong.data.Constant;

public class CustomDialog extends Dialog{
 
	private int style = -1;
	private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();    
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        if(style == Constant.DIALOGTYPE_ONEBUTTON){
        	setContentView(R.layout.dialogonebutton);
        }else if(style == Constant.DIALOGTYPE_TWOBUTTON){
        	setContentView(R.layout.dialogtwobutton);
        }
         
        setLayout();
        setTitle(mTitle);
        setContent(mContent);
        setClickListener(mLeftClickListener , mRightClickListener, mXClickListener);
    }
     
    public CustomDialog(Context context) {
        // Dialog ����� ���� ó�� ���ش�.
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
    }
     
    public CustomDialog(Context context , String title , 
            View.OnClickListener singleListener, int style) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mLeftClickListener = singleListener;
        this.style = style;
        mContext = context;
    }
     
    public CustomDialog(Context context , String title , String content , 
            View.OnClickListener xListener, View.OnClickListener leftListener , View.OnClickListener rightListener, int style) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mContent = content;
        this.mXClickListener = xListener;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;
        this.style = style;
        mContext = context;
    }
    
    public CustomDialog(Context context , String title , String content , String content2,
            View.OnClickListener xListener, View.OnClickListener leftListener , View.OnClickListener rightListener, int style) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mContent = content;
        this.mContent2 = content2;
        this.mXClickListener = xListener;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;
        this.style = style;
        mContext = context;
    }
     
     
    private void setContent(String content){
        mContentView.setText(content);
    }
     
    private void setClickListener(View.OnClickListener left , View.OnClickListener right, View.OnClickListener x){
        if(left!=null && right!=null){
        	mLeftButton.setOnClickListener(left);
            mRightButton.setOnClickListener(right);
        }else if(left!=null && right==null){
        	//mXButton.setOnClickListener(x);
            mLeftButton.setOnClickListener(left);
        }else {
             
        }
    }
     
    private TextView mContentView;
    private Button mLeftButton;
    private Button mRightButton;
    private Button mXButton;
    private String mTitle;
    private String mContent;
    private String mContent2;
    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;
    private View.OnClickListener mXClickListener;
     
    /*
     * Layout
     */
    private void setLayout(){
        mContentView = (TextView) findViewById(R.id.strText);
        mLeftButton = (Button) findViewById(R.id.btnDialogOk);
        if(style != Constant.DIALOGTYPE_ONEBUTTON){
        	mRightButton = (Button) findViewById(R.id.btnDialogCancel);
        }
    }
}
