package com.nhm.timphong.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import org.apache.http.NameValuePair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

public class Util {
	public static MyProgressDialog mProgressDialog;

	public static void showProgress(Context context) {
		//if (mProgressDialogEnabled == false)
		//	return;
		dismissProgress();
		mProgressDialog = new MyProgressDialog(context);
		mProgressDialog.setCancelable(false);
		/*mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface dialog) {
				EnhancedAsyncTask.this.cancel(true);
			}
		});*/
		//String message = context.getString(mTaskProcessingMessage);
		//mProgressDialog.setMessage(message);
		mProgressDialog = MyProgressDialog.show(context,"","",true,true,null);
		mProgressDialog.show();
	}

	public static void dismissProgress() {
		//if (mProgressDialogEnabled == false)
		//	return;
		if(mProgressDialog != null) {
			if (mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
		}
	}

	public static boolean getBoolPrefer(Context context, String prefName){
		SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
		return pref.getBoolean(prefName, false);
	}

	public static void saveBoolPrefer(Context context, String prefName, boolean isAutoChecked){
		SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(prefName, isAutoChecked);
		editor.commit();
	}

	public static void saveStrPrefer(Context context, String prefName, String str){
		SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(prefName, str);
		editor.commit();
	}

	public static String getStrPrefer(Context context, String prefName){
		SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
		return pref.getString(prefName, "");
	}

	public static void saveIntPrefer(Context context, String prefName, int isAutoChecked){
		SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt(prefName, isAutoChecked);
		editor.commit();
	}

	public static int getIntPrefer(Context context, String prefName){
		SharedPreferences pref = context.getSharedPreferences(Util.getApplicationName(context), Context.MODE_PRIVATE);
		return pref.getInt(prefName, 0);
	}

	public static void LogURL(String file, ArrayList<NameValuePair> nameValuePairs){
		String pair = "?";
		for(int i = 0; i < nameValuePairs.size(); i++){
			pair += nameValuePairs.get(i).getName();
			pair += "=";
			pair += nameValuePairs.get(i).getValue();
			pair += "&";
		}
		Log.e("Givecon", "" + file + pair);
	}
	
	public static void unregisterAlarm(Context context, int idx)
	{
	    Intent intent = new Intent();
	    PendingIntent sender = PendingIntent.getBroadcast(context, idx, intent, 0);
	 
	    AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	    manager.cancel(sender);
	}

	// url에 한글 인코딩
	public static String encoderHangulUrl(String url){
		 char[] urlArr = url.toCharArray();
		 StringBuffer sb = new StringBuffer();
		 for (int i = 0; i < urlArr.length; i++) {
			  if(Util.checkHan(urlArr[i]) || String.valueOf(urlArr[i]).equals(" ")){
				   try {
				// 인코딩 그리고 space 별도로 변환
					   sb.append(URLEncoder.encode(String.valueOf(urlArr[i]), "UTF-8").replace("+", "%20"));
				   } catch (UnsupportedEncodingException e) {
					   e.printStackTrace();
				   }
			  }else{
				  sb.append(urlArr[i]);
			  }
		 }
		 return sb.toString();
	}

	// 한글 체크
	public static boolean checkHan(char cValue) {
	 if (cValue >= '\uAC00' && cValue <= '\uD7A3') {
	  return true;
	 } else {
	  return false;
	 }
	}
	
	public static String UrlEncode(String str){
		String encode = "";
		try{
			encode = URLEncoder.encode(str, "UTF-8");
		}catch(Exception e){
		}
		return encode;
	}
	
    /**
     * Base64 인코딩
     */
    public static String getBase64encode(String content){
    	return USecurity.EncBase64StringWith(content);
       // return Base64.encodeToString(content.getBytes(),  Base64.URL_SAFE);
    }
     
    /**
     * Base64 디코딩
     */
    public static String getBase64decode(String content){
    	String strDec= "";
    	try{
    		strDec = USecurity.decBase64String(content);
    	}catch(Exception e){
    		
    	}
    	return strDec;
        //return new String(Base64.decode(content,  Base64.URL_SAFE));
    }

    
	public static String getMacAddress(Context context){  
	    String macAddress="";  
	    boolean bIsWifiOff=false;  
	          
	    WifiManager wfManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);  
	    if(!wfManager.isWifiEnabled()){  
	        wfManager.setWifiEnabled(true);  
	        bIsWifiOff = true;  
	    }  
	          
	    WifiInfo wfInfo = wfManager.getConnectionInfo();  
	    macAddress = wfInfo.getMacAddress();  
	          
	    if(bIsWifiOff){  
	        wfManager.setWifiEnabled(false);  
	        bIsWifiOff = false;  
	    }  
	          
	    return macAddress;  
	}  

	
	//LCD 가로사이즈
	public static int getWidth(Context c){
		Display defaultDisplay = 
			 ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

		return defaultDisplay.getWidth();                           // 가로
	}
	
	//LCD 가로사이즈
		public static int getHeight(Context c){
			Display defaultDisplay = 
				 ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

			return defaultDisplay.getHeight();                           // 가로
		}
	
	//MD5 해시
	public static String makeMD5(String param) {
        StringBuffer md5 = new StringBuffer();
        try { 
            byte[] digest = MessageDigest.getInstance("MD5").digest(param.getBytes());
            for (int i = 0; i < digest.length; i++) {
                md5.append(Integer.toString((digest[i] & 0xf0) >> 4, 16));
                md5.append(Integer.toString(digest[i] & 0x0f, 16));
            }
        } catch(NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
        return md5.toString();
    }


/*
	public static final String makeMD5(final String s) {
	    final String MD5 = "MD5";
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest
	                .getInstance(MD5);
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuilder hexString = new StringBuilder();
	        for (byte aMessageDigest : messageDigest) {
	            String h = Integer.toHexString(0xFF & aMessageDigest);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
*/
	//랜던함수
	public static int random(int min, int max){
		int i = (int) (Math.random()*(max-min+1)+min);
		return i;
	}
			
	
	public static String loadFileAsString(String filePath) throws IOException{
	    StringBuffer fileData = new StringBuffer(1000); 
	    BufferedReader reader = new BufferedReader(new FileReader(filePath)); 
	    char[] buf = new char[1024]; 
	    int numRead=0; 
	    while((numRead=reader.read(buf)) != -1){ 
	        String readData = String.valueOf(buf, 0, numRead); 
	        fileData.append(readData); 
	    } 
	    reader.close(); 
	    return fileData.toString(); 
	} 
	 
	//UUID 만들기 
	public static String getUUID(Context context){ 
	    /*try { 
	        return loadFileAsString("/sys/class/net/eth0/address") 
	            .toUpperCase().substring(0, 17); 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	        return null; 
	    } */
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        //return deviceId;
        return deviceId;
	} 

	//패키지 이름
	public static String getApplicationName(Context context) {
        String name = "?";
        if(context != null) {
        	try {
                PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                name = context.getString(pi.applicationInfo.labelRes);
            } catch (NameNotFoundException e) {
                
            };
        }
        
        return name;
	}
	
	public static String getModelName(){
		return Build.MODEL;
	}
	
	public static String getOSVersion(){
		return Build.VERSION.RELEASE;
	}
	
	public static String getCountryCode(){
		return Locale.getDefault().getCountry();
	}
	
	public static String getLanguageCode(){
		return Locale.getDefault().getLanguage();
	}
	
	
	public static String getVersionNumber(Context context) {
        String version = "?";
        if(context != null) {
	        try {
	            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
	            version = pi.versionName;
	        } catch (NameNotFoundException e) {
	            
	        };
        }
        return version;
	}
	
	public static int getVersionCode(Context context) {
        int code = 0;
        if(context != null) {
	        try {
	            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
	            code = pi.versionCode;
	        } catch (NameNotFoundException e) {
	            
	        };
        }
        return code;
	}
	
	public static String getAdDeviceId(Context context){
		 TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		 return telephony.getDeviceId();
	}
	
	/*public static String getDeviceId(Context context){
		 TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		 if(telephony.getDeviceId().equals(""))
			 return Util.getBase64encode(Util.getMacAddress(context));
		 else
			 return Util.getBase64encode(telephony.getDeviceId());
	}*/
	
	public static String getDeviceId(Context context) {
        String adpopcornDeviceId = "";
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(tm != null){
             adpopcornDeviceId = tm.getDeviceId();
         } else {
              // Wifi 전용 기기의 경우 Mac Address를 사용.
              WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
              if(wm != null) {
                     WifiInfo wifiInfo = wm.getConnectionInfo();
                     adpopcornDeviceId = wifiInfo.getMacAddress();
              }
        }
        return adpopcornDeviceId;
  } 
	
	public static String getPhoneModel(){
		 return Build.PRODUCT + "/" + Build.MODEL;
	}
	
	public static float getScreenWidthDIP(Context context) {
		float dipWidth = 0.0f;
		if(context != null) {
			DisplayMetrics displayMetrics = new DisplayMetrics();
			
			WindowManager wm = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
			if(wm != null) {
				wm.getDefaultDisplay().getMetrics(displayMetrics);
				dipWidth  = displayMetrics.widthPixels  / displayMetrics.density;
			}
		}
		return dipWidth;
	}
	
	public static float getScreenHeightDIP(Context context) {
		float dipHeight = 0.0f;
		if(context != null) {
			DisplayMetrics displayMetrics = new DisplayMetrics();
			
			WindowManager wm = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
			if(wm != null) {
				wm.getDefaultDisplay().getMetrics(displayMetrics);				
				dipHeight  = displayMetrics.heightPixels / displayMetrics.density;
			}
		}
		
		return dipHeight;
	}
	
	public static int getDensity(Context context) {
		int density = DisplayMetrics.DENSITY_DEFAULT;
		DisplayMetrics displayMetrics = new DisplayMetrics();
		
		WindowManager wm = (WindowManager)context.getSystemService(context.WINDOW_SERVICE);
		if(wm != null) {
			wm.getDefaultDisplay().getMetrics(displayMetrics);
			if(displayMetrics.density <= 0.75f) {
				density = DisplayMetrics.DENSITY_LOW;
			} else if(displayMetrics.density <= 1.0f) {
				density = DisplayMetrics.DENSITY_MEDIUM;
			} else if(displayMetrics.density <= 1.5f) {
				density = DisplayMetrics.DENSITY_HIGH ;
			}else{
				density = DisplayMetrics.DENSITY_XHIGH;
			}
			//--- displayMetrics.density : density / 160, 0.75 (ldpi), 1.0 (mdpi), 1.5 (hdpi)
		}
		
		return density;
	}
	
	public static int convertPixelToDPI(int density, int pixel) {
		int dpi = Math.round((float)pixel*(float)DisplayMetrics.DENSITY_DEFAULT/(float)density);
		return dpi;
	}
	
	public static int convertDPIToPixel(int density, int dpi) {
		int pixel = Math.round((float)dpi*(float)density/(float)DisplayMetrics.DENSITY_DEFAULT);
		return pixel;
	}
	
	//값이 있는지
	public static boolean checkStrNull(String str){
		if(str.equals(""))
			return true;
		else 
			return false;
	}
	
	//이메일 형식인지 검사
	public static boolean checkEmailStr(String str){
		if(android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches())
			return true;
		else
			return false;
	}
	
	/** 
	 * 숫자, 영문자만 가능
	 */ 
	public static InputFilter filterAlphaNum = new InputFilter() {
		@Override
	    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) { 
	        Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$"); 
	        if (!ps.matcher(source).matches()) { 
	            return ""; 
	        } 
	        return null; 
	    } 
	}; 
	/** 
	 * 한국어만가능
	 */ 
	public static InputFilter filterKor = new InputFilter() { 
		@Override
	    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) { 
	        Pattern ps = Pattern.compile("^[ㄱ-ㅎ가-힣]+$");
	        if (!ps.matcher(source).matches()) { 
	            return ""; 
	        } 
	        return null; 
	    } 
	};     
	/** 
	 * 한국어, 영어, 숫자
	 */ 
	public static  InputFilter filterKoEnNum = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
			Pattern ps = Pattern.compile("^[a-zA-Z0-9ㄱ-ㅎ가-힣]+$");

			if (!ps.matcher(source).matches()) {
				return "";
			} 
			return null;
		} 
	}; 
	
	//초를 분단위로
	public static String convertToMinutetime(long second){
		long remaindSec;
		int caculMin, caculSec;
		String strCaculSec;
		remaindSec = second/1000;
		caculMin = (int)remaindSec/60;
		caculSec = (int)remaindSec%60;
		if(caculSec < 10)
			strCaculSec = "0" + caculSec;
		else
			strCaculSec = String.valueOf(caculSec);
		return String.valueOf(caculMin + ":" + strCaculSec);
	}
	
	
	//링크 텍스트 뷰
	public static void setFakeLinkTextView(TextView textView, String url, String content){
		textView.setText(Html.fromHtml("<a href=\"" + url + "\">" + content));
		//textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
		
	//링크 텍스트 뷰
	public static void setLinkTextView(TextView textView, String url, String content){
		textView.setText(Html.fromHtml("<a href=\"" + url + "\">" + content));
		//textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	/**
     * Process가 실행중인지 여부 확인.
     * @param context, packageName
     * @return true/false
     */
    public static boolean isRunningProcess(Context context, String packageName) {
        boolean isRunning = false;
        ActivityManager actMng = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);                      
        List<RunningAppProcessInfo> list = actMng.getRunningAppProcesses();     
        for(RunningAppProcessInfo rap : list)                           
        {                                
            if(rap.processName.equals(packageName))                              
            {                                   
                isRunning = true;     
                break;
            }                         
        }
        return isRunning;
    }
    
    /* 어플 설치여부 확인*/
    public static boolean isPackageInstalled(Context ctx, String pkgName) {
    	try {
    		ctx.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES);
    	} catch (NameNotFoundException e) {
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }

	public static long getHour(long time){
		return  time/3600;
	}
	
	public static long getMinute(long time){
		return (time%3600)/60;
	}
	
	public static long getSecond(long time){
		return (time%3600)%60;
	}
}
