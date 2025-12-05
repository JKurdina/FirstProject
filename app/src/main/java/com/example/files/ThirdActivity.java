package com.example.files;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.OutputStream;

public class ThirdActivity extends Activity {

    private static final int AVATAR_SIZE = 512;
    private Bitmap avatarBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageViewAvatar = findViewById(R.id.imageViewAvatar);
        Button btnSave = findViewById(R.id.btn_save);

        imageView.setImageResource(R.drawable.dog);

        avatarBitmap = createCircularAvatar(R.drawable.dog);
        imageViewAvatar.setImageBitmap(avatarBitmap);

        btnSave.setOnClickListener(v -> saveDrawableToGallery(R.drawable.dog, "dog"));
    }

    private Bitmap createCircularAvatar(int drawableId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), drawableId, options);

        options.inJustDecodeBounds = false;
        options.inScaled = false;
        Bitmap original = BitmapFactory.decodeResource(getResources(), drawableId, options);

        int width = original.getWidth();
        int height = original.getHeight();
        int size = Math.min(width, height);

        int offsetX = (width  - size) / 2;
        int offsetY = (height - size) / 2;

        Bitmap squared = Bitmap.createBitmap(original, offsetX, offsetY, size, size);

        Bitmap result = Bitmap.createBitmap(AVATAR_SIZE, AVATAR_SIZE, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        BitmapShader shader = new BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        float radius = AVATAR_SIZE / 2f;
        canvas.drawCircle(radius, radius, radius, paint);

        original.recycle();
        squared.recycle();

        return result;
    }

    private void saveDrawableToGallery(int drawableId, String name) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        saveBitmapToGallery(bitmap, name);
    }

    private void saveBitmapToGallery(Bitmap bitmap, String fileName) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName + ".webp");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/webp");

        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        if (uri != null) {
            try (OutputStream out = getContentResolver().openOutputStream(uri)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSY, 90, out);
                }
                Toast.makeText(this, "Сохранено: " + fileName, Toast.LENGTH_LONG).show();
            } catch (Exception ignored) {}
        }
    }

    @Override
    protected void onDestroy() {
        if (avatarBitmap != null && !avatarBitmap.isRecycled()) {
            avatarBitmap.recycle();
        }
        super.onDestroy();
    }
}
