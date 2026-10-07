package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    String currentPage = "splash";
    String language = "ar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSplash();
    }

    // =========================================================
    // SPLASH
    // الصورة هي الواجهة كاملة
    // =========================================================

    void showSplash() {

        currentPage = "splash";

        FrameLayout frame = fullFrame();

        ImageView image = fullImage(R.drawable.splash_oasis);

        frame.addView(image);

        /*
         * مناطق لمس شفافة فقط.
         * لا يوجد نص أو زر مرئي فوق الصورة.
         */

        // ابدأ الآن
        touch(
                frame,
                15, 62, 70, 10,
                v -> showHome()
        );

        // Français
        touch(
                frame,
                8, 88, 27, 8,
                v -> {
                    language = "fr";
                    showSplash();
                }
        );

        // العربية
        touch(
                frame,
                36, 88, 28, 8,
                v -> {
                    language = "ar";
                    showSplash();
                }
        );

        // English
        touch(
                frame,
                65, 88, 27, 8,
                v -> {
                    language = "en";
                    showSplash();
                }
        );

        setContentView(frame);
    }

    // =========================================================
    // HOME
    // =========================================================

    void showHome() {

        currentPage = "home";

        FrameLayout frame = fullFrame();

        ImageView image = fullImage(R.drawable.oasis_home);

        frame.addView(image);

        /*
         * مناطق لمس شفافة.
         *
         * الترتيب:
         * شراء
         * بيع
         * الطلبات
         * الاتصال
         * الإعدادات
         */

        // شراء
        touch(
                frame,
                5, 45, 42, 12,
                v -> showBuy()
        );

        // بيع
        touch(
                frame,
                53, 45, 42, 12,
                v -> showSell()
        );

        // الطلبات
        touch(
                frame,
                5, 59, 42, 12,
                v -> showOrders()
        );

        // الاتصال
        touch(
                frame,
                53, 59, 42, 12,
                v -> showContact()
        );

        // الإعدادات / القائمة
        touch(
                frame,
                0, 0, 20, 15,
                v -> showSettings()
        );

        // منطقة المنتجات / العروض
        touch(
                frame,
                5, 72, 90, 18,
                v -> showBuy()
        );

        setContentView(frame);
    }

    // =========================================================
    // BUY
    // =========================================================

    void showBuy() {

        currentPage = "buy";

        FrameLayout frame = fullFrame();

        /*
         * في حالة عدم وجود صورة مستقلة للشراء،
         * نستعمل تصميم التطبيق مع صورة التمور.
         * لا نضيف نصوصاً فوق الصورة.
         */

        ImageView image = fullImage(R.drawable.dates_deglet_nour);

        frame.addView(image);

        // الرجوع
        touch(
                frame,
                0, 0, 20, 15,
                v -> showHome()
        );

        // العرض الأول
        touch(
                frame,
                5, 20, 90, 20,
                v -> showOfferDetails()
        );

        // العرض الثاني
        touch(
                frame,
                5, 42, 90, 20,
                v -> showOfferDetails()
        );

        // العرض الثالث
        touch(
                frame,
                5, 64, 90, 20,
                v -> showOfferDetails()
        );

        // الاتصال
        touch(
                frame,
                75, 88, 25, 12,
                v -> showContact()
        );

        setContentView(frame);
    }

    // =========================================================
    // OFFER DETAILS
    // =========================================================

    void showOfferDetails() {

        currentPage = "details";

        FrameLayout frame = fullFrame();

        ImageView image = fullImage(R.drawable.dates_deglet_nour);

        frame.addView(image);

        // رجوع
        touch(
                frame,
                0, 0, 20, 15,
                v -> showBuy()
        );

        // اتصال بالبائع
        touch(
                frame,
                5, 70, 90, 12,
                v -> callPhone()
        );

        // طلب العرض
        touch(
                frame,
                5, 83, 90, 12,
                v -> {
                    Toast.makeText(
                            this,
                            "تم إرسال الطلب",
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );

        setContentView(frame);
    }

    // =========================================================
    // SELL
    // =========================================================

    void showSell() {

        currentPage = "sell";

        FrameLayout frame = fullFrame();

        ImageView image = fullImage(R.drawable.dates_deglet_nour);

        frame.addView(image);

        // رجوع
        touch(
                frame,
                0, 0, 20, 15,
                v -> showHome()
        );

        // اختيار صورة
        touch(
                frame,
                5, 55, 90, 12,
                v -> chooseImage()
        );

        // نشر العرض
        touch(
                frame,
                5, 78, 90, 12,
                v -> {
                    Toast.makeText(
                            this,
                            "تم نشر العرض",
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );

        setContentView(frame);
    }

    // =========================================================
    // ORDERS
    // =========================================================

    void showOrders() {

        currentPage = "orders";

        FrameLayout frame = fullFrame();

        ImageView image = fullImage(R.drawable.oasis_home);

        frame.addView(image);

        // رجوع
        touch(
                frame,
                0, 0, 20, 15,
                v -> showHome()
        );

        // الطلب الأول
        touch(
                frame,
                5, 25, 90, 18,
                v -> showOrderMessage()
        );

        // الطلب الثاني
        touch(
                frame,
                5, 45, 90, 18,
                v -> showOrderMessage()
        );

        // الطلب الثالث
        touch(
                frame,
                5, 65, 90, 18,
                v -> showOrderMessage()
        );

        setContentView(frame);
    }

    void showOrderMessage() {

        Toast.makeText(
                this,
                "تفاصيل الطلب",
                Toast.LENGTH_SHORT
        ).show();
    }

    // =========================================================
    // CONTACT
    // =========================================================

    void showContact() {

        currentPage = "contact";

        FrameLayout frame = fullFrame();

        ImageView image = fullImage(R.drawable.oasis_home);

        frame.addView(image);

        // رجوع
        touch(
                frame,
                0, 0, 20, 15,
                v -> showHome()
        );

        // الهاتف
        touch(
                frame,
                5, 55, 90, 12,
                v -> callPhone()
        );

        // البريد الإلكتروني
        touch(
                frame,
                5, 68, 90, 12,
                v -> sendEmail()
        );

        // الإعدادات
        touch(
                frame,
                5, 81, 90, 12,
                v -> showSettings()
        );

        setContentView(frame);
    }

    // =========================================================
    // SETTINGS
    // =========================================================

    void showSettings() {

        currentPage = "settings";

        FrameLayout frame = fullFrame();

        ImageView image = fullImage(R.drawable.oasis_home);

        frame.addView(image);

        // رجوع
        touch(
                frame,
                0, 0, 20, 15,
                v -> showHome()
        );

        // اللغة
        touch(
                frame,
                5, 25, 90, 13,
                v -> changeLanguage()
        );

        // الإشعارات
        touch(
                frame,
                5, 40, 90, 13,
                v -> Toast.makeText(
                        this,
                        "الإشعارات",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // حول التطبيق
        touch(
                frame,
                5, 55, 90, 13,
                v -> Toast.makeText(
                        this,
                        "DATRIDHA",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // الخصوصية
        touch(
                frame,
                5, 70, 90, 13,
                v -> Toast.makeText(
                        this,
                        "الخصوصية",
                        Toast.LENGTH_SHORT
                ).show()
        );

        // المساعدة
        touch(
                frame,
                5, 85, 90, 10,
                v -> showContact()
        );

        setContentView(frame);
    }

    // =========================================================
    // LANGUAGE
    // =========================================================

    void changeLanguage() {

        if ("ar".equals(language)) {

            language = "fr";

        } else if ("fr".equals(language)) {

            language = "en";

        } else {

            language = "ar";
        }

        showSettings();
    }

    // =========================================================
    // FULL FRAME
    // =========================================================

    FrameLayout fullFrame() {

        FrameLayout frame = new FrameLayout(this);

        frame.setBackgroundColor(Color.BLACK);

        return frame;
    }

    // =========================================================
    // FULL IMAGE
    // =========================================================

    ImageView fullImage(int resource) {

        ImageView image = new ImageView(this);

        image.setImageResource(resource);

        /*
         * الصورة تملأ الشاشة.
         * لا يتم رسم أي نص فوقها.
         */

        image.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );

        image.setClickable(false);

        image.setFocusable(false);

        image.setAdjustViewBounds(false);

        image.setLayoutParams(
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                )
        );

        return image;
    }

    // =========================================================
    // TRANSPARENT TOUCH AREA
    // =========================================================

    void touch(
            FrameLayout frame,
            float xPercent,
            float yPercent,
            float widthPercent,
            float heightPercent,
            View.OnClickListener listener) {

        View area = new View(this);

        /*
         * شفاف 100%.
         * لا يظهر أي شيء فوق الصورة.
         */

        area.setBackgroundColor(
                Color.TRANSPARENT
        );

        area.setOnClickListener(listener);

        FrameLayout.LayoutParams lp =
                new FrameLayout.LayoutParams(
                        1,
                        1
                );

        area.setTag(
                new float[]{
                        xPercent,
                        yPercent,
                        widthPercent,
                        heightPercent
                }
        );

        frame.addView(area, lp);

        area.post(() -> {

            int w = frame.getWidth();
            int h = frame.getHeight();

            int left =
                    (int)(w * xPercent / 100f);

            int top =
                    (int)(h * yPercent / 100f);

            int width =
                    (int)(w * widthPercent / 100f);

            int height =
                    (int)(h * heightPercent / 100f);

            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(
                            width,
                            height
                    );

            params.leftMargin = left;
            params.topMargin = top;

            area.setLayoutParams(params);
        });
    }

    // =========================================================
    // IMAGE PICKER
    // =========================================================

    void chooseImage() {

        Intent intent =
                new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                );

        startActivityForResult(
                intent,
                100
        );
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (
                requestCode == 100
                        && resultCode == RESULT_OK
        ) {

            Toast.makeText(
                    this,
                    "تم اختيار الصورة",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =========================================================
    // PHONE
    // =========================================================

    void callPhone() {

        try {

            Intent intent =
                    new Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse(
                                    "tel:51022448"
                            )
                    );

            startActivity(intent);

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "51022448",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =========================================================
    // EMAIL
    // =========================================================

    void sendEmail() {

        try {

            Intent intent =
                    new Intent(
                            Intent.ACTION_SENDTO
                    );

            intent.setData(
                    Uri.parse(
                            "mailto:ridhatouil1992@gmail.com"
                    )
            );

            intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "DATRIDHA"
            );

            startActivity(intent);

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "ridhatouil1992@gmail.com",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =========================================================
    // BACK
    // =========================================================

    @Override
    public void onBackPressed() {

        if ("details".equals(currentPage)) {

            showBuy();

        } else if (
                "buy".equals(currentPage)
                        || "sell".equals(currentPage)
                        || "orders".equals(currentPage)
                        || "contact".equals(currentPage)
                        || "settings".equals(currentPage)
        ) {

            showHome();

        } else if ("home".equals(currentPage)) {

            showSplash();

        } else if ("splash".equals(currentPage)) {

            finish();

        } else {

            finish();
        }
    }
}
