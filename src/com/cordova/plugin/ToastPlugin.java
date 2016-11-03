package com.cordova.plugin;


import android.util.Log;
import android.widget.TextView;

import com.cordova.R;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by shuhj on 2016/11/2.
 */

public class ToastPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("show".equals(action)) {
            Log.e("TAG","==参数==" + args.toString());
            showToast(args.getString(0), args.getInt(1));
        } else if("submit".equals(action)){
            updateTitle(args.getString(0));
        }
        return super.execute(action, args, callbackContext);
    }

    private void showToast(String text, int type) {
        if (type == 1) {
            android.widget.Toast.makeText(cordova.getActivity(), text, android.widget.Toast.LENGTH_LONG).show();
        } else {
            android.widget.Toast.makeText(cordova.getActivity(), text, android.widget.Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTitle(final String str){
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView title = (TextView) cordova.getActivity().findViewById(R.id.title);
                title.setText(str);
            }
        });
    }

}
