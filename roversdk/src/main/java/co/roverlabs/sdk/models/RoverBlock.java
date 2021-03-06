package co.roverlabs.sdk.models;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import co.roverlabs.sdk.ui.Border;
import co.roverlabs.sdk.ui.BoxModelDimens;
import co.roverlabs.sdk.ui.ImageUtils;
import co.roverlabs.sdk.ui.TextStyle;
import co.roverlabs.sdk.utilities.RoverConstants;

/**
 * Created by SherryYang on 2015-04-30.
 */
public class RoverBlock {

    public static final String TAG = RoverBlock.class.getSimpleName();

    //JSON members
    @SerializedName("type") private String mType;
    @SerializedName("padding") private List<Integer> mPadding;
    @SerializedName("borderWidth") private List<Integer> mBorderWidth;
    @SerializedName("borderColor") private List<Float> mBorderColor;
    @SerializedName("backgroundColor") private List<Float> mBackgroundColor;
    @SerializedName("backgroundImageUrl") private String mBackgroundImageUrl;
    @SerializedName("backgroundContentMode") private String mBackgroundContentMode;
    @SerializedName("imageUrl") private String mImageUrl;
    @SerializedName("imageWidth") private Integer mImageWidth;
    @SerializedName("imageHeight") private Integer mImageHeight;
    @SerializedName("imageOffsetRatio") private Float mImageOffsetRatio;
    @SerializedName("imageAspectRatio") private Float mImageAspectRatio;
    @SerializedName("textContent") private String mTextContent;
    @SerializedName("url") private String mUrl;
    @SerializedName("buttonLabel") private String mButtonLabel;
    @SerializedName("headerTitle") private String mHeaderTitle;
    //Text block text format
    //TODO: Remove hard coded values after testing
    //H1 format
    @SerializedName("h1Font") private String mH1Font;// = "HelveticaNeue-Bold";
    @SerializedName("h1FontSize") private Float mH1FontSize;// = (float)20;
    @SerializedName("h1LineHeight") private Float mH1LineHeight;// = (float)24;
    @SerializedName("h1TextAlign") private String mH1TextAlign;// = "right";
    @SerializedName("h1Color") private List<Float> mH1Color;// = Arrays.asList((float)12, (float)237, (float)53, (float)0.4);
    @SerializedName("h1Margin") private List<Integer> mH1Margin;// = Arrays.asList(0, 0, 20, 0);
    //H2 format
    @SerializedName("h2Font") private String mH2Font;// = "HelveticaNeue-Bold";
    @SerializedName("h2FontSize") private Float mH2FontSize;// = (float)16;
    @SerializedName("h2LineHeight") private Float mH2LineHeight;// = (float)22;
    @SerializedName("h2TextAlign") private String mH2TextAlign;// = "right";
    @SerializedName("h2Color") private List<Float> mH2Color;// = Arrays.asList((float)237, (float)12, (float)196, (float)1);
    @SerializedName("h2Margin") private List<Integer> mH2Margin;// = Arrays.asList(0, 0, 10, 0);
    //P format
    @SerializedName("pFont") private String mPFont;// = "HelveticaNeue-Light";
    @SerializedName("pFontSize") private Float mPFontSize;// = (float)14;
    @SerializedName("pLineHeight") private Float mPLineHeight;// = (float)10;
    @SerializedName("pTextAlign") private String mPTextAlign;// = "right";
    @SerializedName("pColor") private List<Float> mPColor;// = Arrays.asList((float)53, (float)107, (float)232, (float)1);
    @SerializedName("pMargin") private List<Integer> mPMargin;// = Arrays.asList(0, 0, 10, 0);
    //Button block text format
    @SerializedName("labelFont") private String mLabelFont;// = "HelveticaNeue";
    @SerializedName("labelFontSize") private Float mLabelFontSize;// = (float)25;
    @SerializedName("labelLineHeight") private Float mLabelLineHeight;// = (float)18;
    @SerializedName("labelTextAlign") private String mLabelTextAlign;// = "center";
    @SerializedName("labelColor") private List<Float> mLabelFontColor;// = Arrays.asList((float)0, (float)0, (float)0, (float)1);
    @SerializedName("labelMargin") private List<Integer> mLabelMargin;// = Arrays.asList(20, 20, 20, 20);
    //Header block text format
    @SerializedName("titleFont") private String mHeaderFont;
    @SerializedName("titleFontSize") private Float mHeaderFontSize;
    @SerializedName("titleLineHeight") private Float mHeaderLineHeight;
    @SerializedName("titleTextAlign") private String mHeaderTextAlign;
    @SerializedName("titleColor") private List<Float> mHeaderFontColor;
    @SerializedName("titleMargin") private List<Integer> mHeaderMargin;


    //Constructor
    public RoverBlock() { }

    //Getters
    public String getType() { return mType; }
    public String getBackgroundImageUrl() { return mBackgroundImageUrl; }
    public String getmBackgroundContentMode() { return mBackgroundContentMode; }
    public String getImageUrl() { return mImageUrl; }
    public Integer getImageWidth() { return mImageWidth; }
    public Integer getImageHeight() { return mImageHeight; }
    public Float getImageOffsetRatio() { return mImageOffsetRatio; }
    public Float getImageAspectRatio() { return mImageAspectRatio; }
    public String getTextContent() { return mTextContent; }
    public String getUrl() { return mUrl; }
    public String getButtonLabel() { return mButtonLabel; }
    public String getHeaderTitle() { return mHeaderTitle; }

    public BoxModelDimens getPadding(Context con) {

        return new BoxModelDimens(con, mPadding.get(0), mPadding.get(1), mPadding.get(2), mPadding.get(3));
    }

    public BoxModelDimens getBorderWidth(Context con) {

        return new BoxModelDimens(con, mBorderWidth.get(0), mBorderWidth.get(1), mBorderWidth.get(2), mBorderWidth.get(3));
    }

    public int getBorderColor() {

        return ImageUtils.getARGBColor(mBorderColor);
    }

    public int getBackgroundColor() {

        return ImageUtils.getARGBColor(mBackgroundColor);
    }

    public Border getBorder(Context con) {

        return new Border(getBorderWidth(con), getBorderColor());
    }

    public TextStyle getH1TextStyle(Context con) {

        TextStyle style = new TextStyle();
        style.type = RoverConstants.TEXT_H1;
        style.font = mH1Font;
        style.size = mH1FontSize;
        style.lineHeight = ImageUtils.convertDpToPx(con, mH1LineHeight.intValue());
        style.align = mH1TextAlign;
        style.color = ImageUtils.getARGBColor(mH1Color);
        style.margin = new BoxModelDimens(con, mH1Margin.get(0), mH1Margin.get(1), mH1Margin.get(2), mH1Margin.get(3));
        return style;
    }

    public TextStyle getH2TextStyle(Context con) {

        TextStyle style = new TextStyle();
        style.type = RoverConstants.TEXT_H2;
        style.font = mH2Font;
        style.size = mH2FontSize;
        style.lineHeight = ImageUtils.convertDpToPx(con, mH2LineHeight.intValue());
        style.align = mH2TextAlign;
        style.color = ImageUtils.getARGBColor(mH2Color);
        style.margin = new BoxModelDimens(con, mH2Margin.get(0), mH2Margin.get(1), mH2Margin.get(2), mH2Margin.get(3));
        return style;
    }

    public TextStyle getPTextStyle(Context con) {

        TextStyle style = new TextStyle();
        style.type = RoverConstants.TEXT_P;
        style.font = mPFont;
        style.size = mPFontSize;
        style.lineHeight = ImageUtils.convertDpToPx(con, mPLineHeight.intValue());
        style.align = mPTextAlign;
        style.color = ImageUtils.getARGBColor(mPColor);
        style.margin = new BoxModelDimens(con, mPMargin.get(0), mPMargin.get(1), mPMargin.get(2), mPMargin.get(3));
        return style;
    }

    public List<TextStyle> getTextBlockStyles(Context con) {

        List<TextStyle> textStyles = new ArrayList<>();
        textStyles.add(getH1TextStyle(con));
        textStyles.add(getH2TextStyle(con));
        textStyles.add(getPTextStyle(con));
        return textStyles;
    }
    
    public TextStyle getLabelTextStyle(Context con) {

        TextStyle style = new TextStyle();
        style.type = RoverConstants.TEXT_DIV;
        style.font = mLabelFont;
        style.size = mLabelFontSize;
        style.lineHeight = ImageUtils.convertDpToPx(con, mLabelLineHeight.intValue());
        style.align = mLabelTextAlign;
        style.color = ImageUtils.getARGBColor(mLabelFontColor);
        style.margin = new BoxModelDimens(con, mLabelMargin.get(0), mLabelMargin.get(1), mLabelMargin.get(2), mLabelMargin.get(3));
        return style;
    }

    public TextStyle getHeaderTextStyle(Context con) {

        TextStyle style = new TextStyle();
        style.type = RoverConstants.TEXT_DIV;
        style.font = mHeaderFont;
        style.size = mHeaderFontSize;
        style.lineHeight = ImageUtils.convertDpToPx(con, mHeaderLineHeight.intValue());
        style.align = mHeaderTextAlign;
        style.color = ImageUtils.getARGBColor(mHeaderFontColor);
        style.margin = new BoxModelDimens(con, mHeaderMargin.get(0), mHeaderMargin.get(1), mHeaderMargin.get(2), mHeaderMargin.get(3));
        return style;
    }
}
