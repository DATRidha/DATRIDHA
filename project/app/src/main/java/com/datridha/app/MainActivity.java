package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.*;

public class MainActivity extends Activity {

    private String currentPage = "splash";
    private String language = "ar";

    private final int GREEN = Color.rgb(23, 107, 58);
    private final int LIGHT_GREEN = Color.rgb(46, 139, 87);
    private final int GOLD = Color.rgb(212, 175, 55);
    private final int CREAM = Color.rgb(249, 244, 236);
    private final int BROWN = Color.rgb(91, 58, 30);
    private final int WHITE = Color.WHITE;

    private EditText sellQuantity;
    private EditText sellPrice;
    private ImageView selectedImageView;
    private Uri selectedImageUri;

    private static final int PICK_IMAGE_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(GREEN);
        getWindow().setNavigationBarColor(GREEN);

        showSplash();
    }

    // =========================================================
    // الأدوات العامة
    // =========================================================

    private GradientDrawable roundedBackground(
            int color,
            float radius
    ) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    private TextView makeText(
            String value,
            float size,
            int color
    ) {
        TextView text = new TextView(this);
        text.setText(value);
        text.setTextSize(size);
        text.setTextColor(color);
        text.setGravity(Gravity.CENTER);
        text.setPadding(8, 8, 8, 8);
        return text;
    }

    private Button makeButton(String title) {
        Button button = new Button(this);

        button.setText(title);
        button.setTextSize(17);
        button.setTextColor(WHITE);
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER);
        button.setPadding(12, 4, 12, 4);

        button.setBackground(
                roundedBackground(GREEN, 45)
        );

        return button;
    }

    private void addButton(
            LinearLayout root,
            Button button
    ) {
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(0, 5, 0, 5);
        root.addView(button, params);
    }

    private void addSpace(
            LinearLayout root,
            int height
    ) {
        Space space = new Space(this);

        root.addView(
                space,
                new LinearLayout.LayoutParams(
                        1,
                        height
                )
        );
    }

    private LinearLayout baseLayout() {
        ScrollView scroll = new ScrollView(this);

        scroll.setFillViewport(true);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(20, 20, 20, 30);
        content.setBackgroundColor(CREAM);

        scroll.addView(content);

        setContentView(scroll);

        return content;
    }

    private void addHeader(
            LinearLayout root,
            String title
    ) {
        TextView header = makeText(
                title,
                25,
                WHITE
        );

        header.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        header.setBackground(
                roundedBackground(GREEN, 35)
        );

        header.setPadding(10, 18, 10, 18);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(0, 0, 0, 20);

        root.addView(header, params);
    }

    // =========================================================
    // شاشة البداية
    // =========================================================

    private void showSplash() {

        currentPage = "splash";

        Window window = getWindow();

        // نبقي شريط التنقل السفلي ظاهرًا حتى لا تتداخل الأزرار معه.
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(GREEN);

        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(CREAM);

        // الخلفية الأصلية التي رفعها المستخدم.
        ImageView background = new ImageView(this);

        background.setImageResource(
                R.drawable.image_2bd7123b
        );

        background.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );

        root.addView(
                background,
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                )
        );

        // لا نضيف شعارًا آخر لأن الصورة تحتوي على شعار DATRIDHA.
        LinearLayout phrases = new LinearLayout(this);

        phrases.setOrientation(
                LinearLayout.VERTICAL
        );

        phrases.setGravity(Gravity.CENTER);
        phrases.setPadding(12, 0, 12, 0);

        TextView phrase1 = makeText(
                "دڤلة نور تونسية أصيلة",
                27,
                BROWN
        );

        phrase1.setTypeface(
                Typeface.create(
                        "serif",
                        Typeface.BOLD_ITALIC
                )
        );

        phrase1.setShadowLayer(
                2,
                0,
                1,
                Color.WHITE
        );

        phrases.addView(
                phrase1,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        50
                )
        );

        TextView phrase2 = makeText(
                "دڤلة ڤبلي",
                23,
                BROWN
        );

        phrase2.setTypeface(
                Typeface.create(
                        "serif",
                        Typeface.BOLD_ITALIC
                )
        );

        phrase2.setShadowLayer(
                2,
                0,
                1,
                Color.WHITE
        );

        phrases.addView(
                phrase2,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        42
                )
        );

        TextView phrase3 = makeText(
                "بيع - شراء - تعامل",
                18,
                BROWN
        );

        phrase3.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        phrase3.setShadowLayer(
                2,
                0,
                1,
                Color.WHITE
        );

        phrases.addView(
                phrase3,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        40
                )
        );

        FrameLayout.LayoutParams phrasesParams =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                );

        phrasesParams.gravity =
                Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        root.addView(phrases, phrasesParams);

        // مجموعة الأزرار السفلية.
        LinearLayout bottomContent = new LinearLayout(this);

        bottomContent.setOrientation(
                LinearLayout.VERTICAL
        );

        bottomContent.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        bottomContent.setPadding(16, 8, 16, 12);

        FrameLayout.LayoutParams bottomParams =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                );

        bottomParams.gravity =
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        root.addView(bottomContent, bottomParams);

        String startTitle;

        if ("fr".equals(language)) {
            startTitle = "Commencer maintenant";
        } else if ("en".equals(language)) {
            startTitle = "Start now";
        } else {
            startTitle = "ابدأ الآن";
        }

        Button startButton = makeButton(startTitle);

        startButton.setTextSize(19);

        startButton.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        startButton.setBackground(
                roundedBackground(GREEN, 55)
        );

        startButton.setOnClickListener(v -> showHome());

        LinearLayout.LayoutParams startParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        56
                );

        startParams.setMargins(20, 0, 20, 8);

        bottomContent.addView(startButton, startParams);

        // أزرار اللغات.
        LinearLayout languages = new LinearLayout(this);

        languages.setOrientation(
                LinearLayout.HORIZONTAL
        );

        languages.setGravity(Gravity.CENTER);

        Button ar = makeButton("العربية");
        Button fr = makeButton("Français");
        Button en = makeButton("English");

        ar.setTextSize(13);
        fr.setTextSize(13);
        en.setTextSize(13);

        ar.setOnClickListener(v -> {
            language = "ar";
            showSplash();
        });

        fr.setOnClickListener(v -> {
            language = "fr";
            showSplash();
        });

        en.setOnClickListener(v -> {
            language = "en";
            showSplash();
        });

        languages.addView(
                ar,
                new LinearLayout.LayoutParams(
                        0, 44, 1
                )
        );

        languages.addView(
                fr,
                new LinearLayout.LayoutParams(
                        0, 44, 1
                )
        );

        languages.addView(
                en,
                new LinearLayout.LayoutParams(
                        0, 44, 1
                )
        );

        bottomContent.addView(languages);

        setContentView(root);

        // وضع العبارات تحت اسم DATRIDHA الموجود في الصورة.
        root.post(() -> {

            int screenHeight = root.getHeight();

            FrameLayout.LayoutParams params =
                    (FrameLayout.LayoutParams)
                            phrases.getLayoutParams();

            params.topMargin =
                    (int) (screenHeight * 0.34f);

            phrases.setLayoutParams(params);
        });
    }

    // =========================================================
    // الصفحة الرئيسية
    // =========================================================

    private void showHome() {

        currentPage = "home";

        getWindow().getDecorView().setSystemUiVisibility(0);
        getWindow().setStatusBarColor(GREEN);
        getWindow().setNavigationBarColor(GREEN);

        LinearLayout root = baseLayout();

        addHeader(root, "DATRIDHA");

        TextView welcome = makeText(
                "مرحباً بكم في DATRIDHA",
                24,
                GREEN
        );

        welcome.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        root.addView(welcome);

        root.addView(
                makeText(
                        "منصة تجارة دقلة النور من بشني",
                        17,
                        BROWN
                )
        );

        addSpace(root, 15);

        Button buy = makeButton("🛒 شراء دقلة النور");
        buy.setOnClickListener(v -> showBuy());
        addButton(root, buy);

        Button sell = makeButton("📦 بيع دقلة النور");
        sell.setOnClickListener(v -> showSell());
        addButton(root, sell);

        Button orders = makeButton("📋 طلباتي");
        orders.setOnClickListener(v -> showOrders());
        addButton(root, orders);

        Button contact = makeButton("📞 اتصل بنا");
        contact.setOnClickListener(v -> showContact());
        addButton(root, contact);

        Button settings = makeButton("⚙ الإعدادات");
        settings.setOnClickListener(v -> showSettings());
        addButton(root, settings);

        setContentView(root);
    }

    // =========================================================
    // صفحة الشراء
    // =========================================================

    private void showBuy() {

        currentPage = "buy";

        LinearLayout root = baseLayout();

        addHeader(root, "شراء دقلة النور");

        addProduct(
                root,
                "دقلة النور من بشني",
                "تمر فاخر • جودة عالية",
                "السعر حسب الكمية"
        );

        addProduct(
                root,
                "دقلة نور ممتازة",
                "اختيار المنتج مباشرة من المصدر",
                "الدفع عند الاستلام"
        );

        Button back = makeButton("← العودة");
        back.setOnClickListener(v -> showHome());
        addButton(root, back);

        setContentView(root);
    }

    private void addProduct(
            LinearLayout root,
            String title,
            String description,
            String price
    ) {

        LinearLayout card = new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(20, 20, 20, 20);

        card.setBackground(
                roundedBackground(WHITE, 30)
        );

        TextView titleText = makeText(
                title,
                21,
                GREEN
        );

        titleText.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        card.addView(titleText);

        card.addView(
                makeText(description, 16, BROWN)
        );

        card.addView(
                makeText(price, 17, GOLD)
        );

        Button details = makeButton("عرض التفاصيل");

        details.setOnClickListener(
                v -> showOfferDetails()
        );

        card.addView(details);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(10, 10, 10, 20);

        root.addView(card, params);
    }

    // =========================================================
    // تفاصيل العرض
    // =========================================================

    private void showOfferDetails() {

        currentPage = "details";

        LinearLayout root = baseLayout();

        addHeader(root, "تفاصيل العرض");

        root.addView(
                makeText(
                        "دقلة النور من بشني",
                        25,
                        GREEN
                )
        );

        root.addView(
                makeText(
                        "تمر فاخر من واحات بشني – الفوار – قبلي",
                        17,
                        BROWN
                )
        );

        root.addView(
                makeText(
                        "تجارة مباشرة • جودة • ثقة",
                        17,
                        GOLD
                )
        );

        Button call = makeButton("📞 الاتصال بالبائع");
        call.setOnClickListener(v -> callPhone());
        addButton(root, call);

        Button order = makeButton("🛒 طلب المنتج");

        order.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "تم تسجيل طلبك على هذا الجهاز",
                        Toast.LENGTH_SHORT
                ).show()
        );

        addButton(root, order);

        Button back = makeButton("← العودة");
        back.setOnClickListener(v -> showBuy());
        addButton(root, back);

        setContentView(root);
    }

    // =========================================================
    // صفحة البيع
    // =========================================================

    private void showSell() {

        currentPage = "sell";

        LinearLayout root = baseLayout();

        addHeader(root, "بيع دقلة النور");

        sellQuantity = new EditText(this);

        sellQuantity.setHint("الكمية بالكيلوغرام");

        sellQuantity.setInputType(
                android.text.InputType.TYPE_CLASS_NUMBER
        );

        root.addView(sellQuantity);

        sellPrice = new EditText(this);

        sellPrice.setHint("السعر للكيلوغرام");

        sellPrice.setInputType(
                android.text.InputType.TYPE_CLASS_NUMBER
                        | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        );

        root.addView(sellPrice);

        selectedImageView = new ImageView(this);

        selectedImageView.setVisibility(View.GONE);

        selectedImageView.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );

        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        220
                );

        imageParams.setMargins(0, 10, 0, 10);

        root.addView(selectedImageView, imageParams);

        Button photo = makeButton("📷 إضافة صورة");

        // المعرض لا يُفتح إلا عند الضغط على هذا الزر.
        photo.setOnClickListener(v -> chooseImage());

        addButton(root, photo);

        Button publish = makeButton("نشر العرض");

        publish.setOnClickListener(v -> {

            String quantity =
                    sellQuantity.getText().toString().trim();

            String price =
                    sellPrice.getText().toString().trim();

            if (quantity.isEmpty() || price.isEmpty()) {

                Toast.makeText(
                        this,
                        "يرجى إدخال الكمية والسعر",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Toast.makeText(
                    this,
                    "تم إعداد العرض. النشر هنا محلي فقط.",
                    Toast.LENGTH_LONG
            ).show();
        });

        addButton(root, publish);

        Button back = makeButton("← العودة");
        back.setOnClickListener(v -> showHome());
        addButton(root, back);

        setContentView(root);
    }

    // =========================================================
    // استقبال الصورة المختارة من المعرض
    // =========================================================

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data
    ) {
        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            selectedImageUri = data.getData();

            if (selectedImageView != null) {

                selectedImageView.setImageURI(
                        selectedImageUri
                );

                selectedImageView.setVisibility(
                        View.VISIBLE
                );
            }

            Toast.makeText(
                    this,
                    "تم اختيار الصورة",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void chooseImage() {

        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );

        startActivityForResult(
                intent,
                PICK_IMAGE_REQUEST
        );
    }

    // =========================================================
    // صفحة الطلبات
    // =========================================================

    private void showOrders() {

        currentPage = "orders";

        LinearLayout root = baseLayout();

        addHeader(root, "طلباتي");

        root.addView(
                makeText(
                        "لا توجد طلبات حالياً",
                        20,
                        BROWN
                )
        );

        Button back = makeButton("← العودة");
        back.setOnClickListener(v -> showHome());
        addButton(root, back);

        setContentView(root);
    }

    // =========================================================
    // صفحة الاتصال
    // =========================================================

    private void showContact() {

        currentPage = "contact";

        LinearLayout root = baseLayout();

        addHeader(root, "اتصل بنا");

        root.addView(
                makeText("DATRIDHA", 30, GOLD)
        );

        root.addView(
                makeText(
                        "دقلة النور من بشني",
                        21,
                        GREEN
                )
        );

        root.addView(
                makeText(
                        "للاستفسار والطلبات",
                        17,
                        BROWN
                )
        );

        Button phone = makeButton("📞 +216 51022448");
        phone.setOnClickListener(v -> callPhone());
        addButton(root, phone);

        Button email = makeButton(
                "✉ ridhatouil1992@gmail.com"
        );

        email.setOnClickListener(v -> sendEmail());
        addButton(root, email);

        Button back = makeButton("← العودة");
        back.setOnClickListener(v -> showHome());
        addButton(root, back);

        setContentView(root);
    }

    // =========================================================
    // صفحة الإعدادات
    // =========================================================

    private void showSettings() {

        currentPage = "settings";

        LinearLayout root = baseLayout();

        addHeader(root, "الإعدادات");

        root.addView(
                makeText("اللغة", 20, GREEN)
        );

        Button ar = makeButton("العربية");
        Button fr = makeButton("Français");
        Button en = makeButton("English");

        ar.setOnClickListener(v -> {
            language = "ar";
            showSettings();
        });

        fr.setOnClickListener(v -> {
            language = "fr";
            showSettings();
        });

        en.setOnClickListener(v -> {
            language = "en";
            showSettings();
        });

        addButton(root, ar);
        addButton(root, fr);
        addButton(root, en);

        Button back = makeButton("← العودة");
        back.setOnClickListener(v -> showHome());
        addButton(root, back);

        setContentView(root);
    }

    // =========================================================
    // الاتصال الهاتفي والبريد الإلكتروني
    // =========================================================

    private void callPhone() {

        Intent intent = new Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:+21651022448")
        );

        startActivity(intent);
    }

    private void sendEmail() {

        Intent intent = new Intent(
                Intent.ACTION_SENDTO
        );

        intent.setData(
                Uri.parse("mailto:ridhatouil1992@gmail.com")
        );

        intent.putExtra(
                Intent.EXTRA_SUBJECT,
                "DATRIDHA"
        );

        startActivity(intent);
    }

    // =========================================================
    // زر الرجوع في الهاتف
    // =========================================================

    @Override
    public void onBackPressed() {

        if (currentPage.equals("splash")) {

            super.onBackPressed();

        } else if (currentPage.equals("home")) {

            showSplash();

        } else if (currentPage.equals("buy")
                || currentPage.equals("sell")
                || currentPage.equals("orders")
                || currentPage.equals("contact")
                || currentPage.equals("settings")) {

            showHome();

        } else if (currentPage.equals("details")) {

            showBuy();

        } else {

            showHome();
        }
    }
}
