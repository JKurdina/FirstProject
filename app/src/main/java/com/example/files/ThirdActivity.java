package com.example.files;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
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

    private static final int AVATAR_SIZE = 1024;
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
//        также есть функция получения изображения из файла decodeFile(String pathName)
        BitmapFactory.decodeResource(getResources(), drawableId, options);

        options.inJustDecodeBounds = false;
        options.inScaled = false;
        Bitmap original = BitmapFactory.decodeResource(getResources(), drawableId, options);

        float scale = Math.max(
                (float) AVATAR_SIZE / original.getWidth(),
                (float) AVATAR_SIZE / original.getHeight()
        );

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);

        float x = (AVATAR_SIZE - original.getWidth() * scale) / 2;
        float y = (AVATAR_SIZE - original.getHeight() * scale) / 2;
        matrix.postTranslate(x, y);

        Bitmap result = Bitmap.createBitmap(AVATAR_SIZE, AVATAR_SIZE, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);

        BitmapShader shader = new BitmapShader(original, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shader.setLocalMatrix(matrix);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(shader);

        canvas.drawCircle(AVATAR_SIZE/2f, AVATAR_SIZE/2f, AVATAR_SIZE/2f, paint);

        original.recycle();
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
