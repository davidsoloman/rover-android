package co.roverlabs.sdk.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.util.List;

import co.roverlabs.sdk.R;
import co.roverlabs.sdk.managers.RoverVisitManager;
import co.roverlabs.sdk.models.RoverBlock;
import co.roverlabs.sdk.models.RoverCard;
import co.roverlabs.sdk.models.RoverView;
import co.roverlabs.sdk.utilities.RoverConstants;

/**
 * Created by arsent on 15-05-19.
 */
public class PicassoUtils {

    public static boolean isInitialized = false;

    public static void init(Context appContext){
        if (isInitialized) return;

        Picasso.with(appContext);
        isInitialized = true;
    }

    /**
     * Prefetch images so they won't use network when the user launches the CardActivity
     *
     * @param appContext
     */
    public static void prefetchImages(final Context appContext) {
        init(appContext);

        List<RoverCard> cards = RoverVisitManager.getInstance(appContext).getLatestVisit().getAccumulatedCards();


        for (RoverCard card : cards) {
            RoverView listView = card.getListView();

            //load background images
            String imageUrl = listView.getBackgroundImageUrl();
            String imageMode = listView.getBackgroundContentMode();
            if (!TextUtils.isEmpty(imageUrl)) {
                getPicassoRequestCreator(appContext, imageUrl, imageMode).fetch();
            }

            //load block images
            for (RoverBlock block : listView.getBlocks()) {
                String blockImageUrl = block.getImageUrl(UiUtils.getDeviceWidth(appContext));

                if (blockImageUrl != null){
                    Picasso.with(appContext).load(blockImageUrl).fetch();
                }
            }
        }
    }

    public static void loadBlockImage(final Context context, final ImageView imageView, final RoverBlock block) {
        init(context);

        String blockImageUrl = block.getImageUrl(UiUtils.getDeviceWidth(context));
        Picasso.with(imageView.getContext())
                .load(blockImageUrl)
                .into(new Target() {
                    @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        //may want to do some appear animation
                        imageView.setAdjustViewBounds(true);
                        imageView.setImageBitmap(bitmap);

                    }
                    @Override public void onBitmapFailed(Drawable errorDrawable) { }
                    @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
                        //force the image to render the size we are setting.
                        imageView.setAdjustViewBounds(false);

                        //calculating estimated height of the block using aspect ratio
                        int estimatedWidth = UiUtils.getDeviceWidth(context)
                                - block.getPadding(context).left
                                - block.getPadding(context).right
                                - block.getBorderWidth(context).left
                                - block.getBorderWidth(context).right;
                        int estimatedHeight = (int) (estimatedWidth / block.getImageAspectRatio());

                        imageView.setMinimumHeight(UiUtils.convertDpToPx(context, estimatedHeight));
                    }
                });
    }

    /**
     *
     * Prefetch images into the view
     *
     * @param imageView
     * @param imageUrl
     * @param imageMode
     */
    public static void loadBackgroundImage(final ImageView imageView, String imageUrl, String imageMode) {

       if (imageView != null){
           return;
       }

        RequestCreator requestCreator = getPicassoRequestCreator(imageView.getContext(), imageUrl, imageMode);
        requestCreator.into(imageView);

        switch(imageMode) {

            case RoverConstants.IMAGE_MODE_STRETCH:
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;

            //TODO: Tile mode
            //case RoverConstants.IMAGE_MODE_TILE:
            //    break;

            case RoverConstants.IMAGE_MODE_FILL:
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;

            case RoverConstants.IMAGE_MODE_FIT:
                break;

            //TODO: Original size
            //case RoverConstants.IMAGE_MODE_ORIGINAL:
            //    imageView.setImageDrawable(backgroundDrawable);
            //    imageView.setScaleType(ImageView.ScaleType.CENTER);

            default:
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    /**
     *
     * Util function for creating Picasso RequestCreator so we can use for both pre-fetching and showing the image
     *
     * @param context
     * @param imageUrl
     * @param imageMode
     * @return
     */
    private static RequestCreator getPicassoRequestCreator(final Context context, String imageUrl, String imageMode) {
        init(context);

        RequestCreator requestCreator = null;

        switch(imageMode) {

            case RoverConstants.IMAGE_MODE_STRETCH:
                requestCreator = Picasso.with(context).load(imageUrl).fit();
                break;

            //TODO: Tile mode
            //case RoverConstants.IMAGE_MODE_TILE:
            //    break;

            case RoverConstants.IMAGE_MODE_FILL:
                requestCreator = Picasso.with(context).load(imageUrl).fit().centerCrop();
                break;

            case RoverConstants.IMAGE_MODE_FIT:
                requestCreator = Picasso.with(context).load(imageUrl).fit().centerInside();
                break;

            //TODO: Original size
            //case RoverConstants.IMAGE_MODE_ORIGINAL:
            //    imageView.setImageDrawable(backgroundDrawable);
            //    imageView.setScaleType(ImageView.ScaleType.CENTER);

            default:
                requestCreator = Picasso.with(context).load(imageUrl).fit().centerCrop();
        }

        return requestCreator;
    }
}
