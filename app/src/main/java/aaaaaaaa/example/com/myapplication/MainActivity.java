package aaaaaaaa.example.com.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import org.xmlpull.v1.XmlPullParserException;

public class MainActivity extends AppCompatActivity {


    //author by qq105620280 18811132511
    private Drawable mShutter;
    private Drawable mShutterPoint;
    private View mButton;
    private Canvas mCanvas;
    private Bitmap bitmap;


    private SeekBar mSeekbar;
    private LayerDrawable mLayerDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.shutter_pointer);
        mShutter= getResources().getDrawable(R.drawable.shutter);
        mShutterPoint = getResources().getDrawable(R.drawable.shutter_point);
        mCanvas = new Canvas();
//        getShutterBitmap ();

//        GradientDrawable.createFromXml()


        mLayerDrawable = (LayerDrawable)getResources().getDrawable(R.drawable.red_tumb_shutter);
//        LayerDrawable.
        mSeekbar = (SeekBar)findViewById(R.id.acs);

        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int thumbOffset = mSeekbar.getThumbOffset();
                Log.e("thumbOffset","thumbOffset is :thumbOffset"+thumbOffset);
                float max = mSeekbar.getMax();
                float range = max / 180f;
                float v = progress / range;
                bitmap = drawableToBitmap(v);
                mCanvas.rotate(180+v);
                mShutterPoint.draw(mCanvas);
                mLayerDrawable.setDrawableByLayerId(R.id.backgroud,new BitmapDrawable(bitmap));
//                seekBar.setThumbOffset(0);
                seekBar.setThumb(mLayerDrawable);
                mButton.setBackground(new BitmapDrawable(bitmap));
                mSeekbar.setThumbOffset(0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


//    public  Bitmap drawableToBitmap(float radius) {
//        Bitmap bitmap = Bitmap.createBitmap(
//                mShutter.getIntrinsicWidth(),
//                mShutter.getIntrinsicHeight(),
//                Bitmap.Config.ARGB_8888);
//        mCanvas.setBitmap(bitmap);
//        mShutter.setBounds(0, 0, mShutter.getIntrinsicWidth(), mShutter.getIntrinsicHeight());
//        mShutter.draw(mCanvas);
//
//        mShutterPoint.setBounds(0,0,mShutter.getIntrinsicWidth()/2-175,30);
//        mCanvas.translate(mShutter.getIntrinsicWidth()/2,mShutter.getIntrinsicHeight()/2-15);
//        return bitmap;
//    }
    private void getShutterBitmap (){
        Canvas canvas = new Canvas();
        bitmap = Bitmap.createBitmap(
                180,
                180,
                Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        mShutter.setBounds(0, 0, mShutter.getIntrinsicWidth(), mShutter.getIntrinsicHeight());
        mShutter.draw(canvas);
    }

    public  Bitmap drawableToBitmap(float range) {
        Bitmap bitmap = Bitmap.createBitmap(
                mShutter.getIntrinsicWidth(),
                mShutter.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(null);
        mCanvas.setBitmap(bitmap);
        mShutter.setBounds(0, 0, mShutter.getIntrinsicWidth(), mShutter.getIntrinsicHeight());
        mShutter.draw(mCanvas);
        mShutterPoint.setBounds(0,0,mShutter.getIntrinsicWidth()/2-75,30);
        mCanvas.translate(mShutter.getIntrinsicWidth()/2,mShutter.getIntrinsicHeight()/2+30/2 - range/180 *30);
        return bitmap;
    }
}
