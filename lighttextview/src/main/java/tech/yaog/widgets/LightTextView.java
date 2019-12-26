package tech.yaog.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

/**
 * 轻量化 TextView
 */
public class LightTextView extends View {

    private String mString;
    private int mFontColor = Color.BLACK;
    private float mTextSize = 0;
    private int mGravity = Gravity.LEFT;
    private int mTextStyle = 0;

    private TextPaint mTextPaint;

    private float mTextWidth, mTextHeight, mTextBaseline, mOffsetLeft, mWidth = -1, mHeight = -1;
    private int mWidthMeasureSpec, mHeightMeasureSpec;


    public LightTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public LightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LightTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.LightTextView, defStyle, 0);

        // 默认字号 14sp
        mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getContext().getResources().getDisplayMetrics());

        mString = a.getString(R.styleable.LightTextView_android_text);
        mTextSize = a.getDimension(R.styleable.LightTextView_android_textSize, mTextSize);
        mFontColor = a.getColor(R.styleable.LightTextView_android_textColor, mFontColor);
        mGravity = a.getInt(R.styleable.LightTextView_android_gravity, mGravity);
        mTextStyle = a.getInt(R.styleable.LightTextView_android_textStyle, mTextStyle);

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mFontColor);

        Typeface font = Typeface.create(Typeface.DEFAULT, mTextStyle);
        mTextPaint.setTypeface(font);

        if (mString == null) {
            mTextWidth = 0;
        }
        else {
            mTextWidth = mTextPaint.measureText(mString);
        }

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextBaseline = -fontMetrics.top;
        mTextHeight = fontMetrics.bottom - fontMetrics.top;
    }

    private void calcOffset() {
        if (mGravity == Gravity.CENTER) {
            mOffsetLeft = (mWidth - mTextWidth) / 2f;
        }
        else if (mGravity == Gravity.RIGHT) {
            mOffsetLeft = mWidth - mTextWidth;
        }
        else {
            mOffsetLeft = 0;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidthMeasureSpec = widthMeasureSpec;
        mHeightMeasureSpec = heightMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = resolveSize((int) (mTextWidth + 0.5f), widthMeasureSpec);
        int height = resolveSize((int) (mTextHeight + 0.5f), heightMeasureSpec);
        setMeasuredDimension(width, height);
        mWidth = width;
        mHeight = height;

        calcOffset();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mString != null) {
            canvas.drawText(mString,
                    mOffsetLeft,
                    mTextBaseline,
                    mTextPaint);
        }
    }

    /**
     * Set Text
     * @param text Text
     */
    public void setText(String text) {
        mString = text;
        invalidateTextPaintAndMeasurements();

        calcOffset();
        postInvalidate();
    }

    public String getText() {
        return mString;
    }

    public void setText(int resId) {
        setText(getContext().getResources().getText(resId).toString());
    }

    public void setTextSize(float textSize) {
        mTextSize = textSize;
        invalidateTextPaintAndMeasurements();

        calcOffset();
        postInvalidate();
    }

    public void setTextColor(int color) {
        mFontColor = ColorStateList.valueOf(color).getDefaultColor();
        invalidateTextPaintAndMeasurements();

        postInvalidate();
    }

    public void setGravity(int gravity) {
        mGravity = gravity;
        invalidateTextPaintAndMeasurements();

        calcOffset();
        postInvalidate();
    }

    public void setTextStyle(int textStyle) {
        mTextStyle = textStyle;
        invalidateTextPaintAndMeasurements();

        calcOffset();
        postInvalidate();
    }
}
