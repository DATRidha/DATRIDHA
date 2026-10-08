package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

    private String currentPage = "splash";
    private String language = "ar";

    private final int GREEN = Color.rgb(23,107,58);
    private final int LIGHT_GREEN = Color.rgb(46,139,87);
    private final int GOLD = Color.rgb(212,175,55);
    private final int CREAM = Color.rgb(249,244,236);
    private final int BROWN = Color.rgb(91,58,30);
    private final int WHITE = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(GREEN);
        getWindow().setNavigationBarColor(GREEN);

        showSplash();
    }

    private TextView text(String value, float size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setPadding(8, 8, 8, 8);
        return t;
    }

    private GradientDrawable bg(int color, float radius) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(radius);
        return g;
    }

    private Button button(String title) {
        Button b = new Button(this);
        b.setText(title);
        b.setTextSize(16);
        b.setTextColor(WHITE);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setPadding(15, 5, 15, 5);
        b.setBackground(bg(GREEN, 45));
        return b;
    }

    /* =========================================================
       الواجهة الأولى الجديدة
       الصورة تكون خلفية كاملة، والأزرار حقيقية
       ========================================================= */

    private void showSplash() {

        currentPage = "splash";

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        FrameLayout root = new FrameLayout(this);

        /*
         * صورة الواجهة الجديدة
         * الملف:
         * res/drawable/splash_oasis.png
         */
        ImageView background = new ImageView(this);

        background.setImageResource(R.drawable.splash_oasis);

        background.setScaleType(
                ImageView.ScaleType.CENTER_CROP
        );

        root.addView(
                background,
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                )
        );

        /*
         * طبقة شفافة خفيفة فوق الصورة
         * حتى يظهر النص والأزرار بوضوح
         */
        View shade = new View(this);

        GradientDrawable shadeBg =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{
                                0x22000000,
                                0x55000000,
                                0x99000000
                        }
                );

        shade.setBackground(shadeBg);

        root.addView(
                shade,
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                )
        );

        /*
         * المحتوى
         */
        LinearLayout content =
                new LinearLayout(this);

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        content.setPadding(
                25,
                55,
                25,
                25
        );

        FrameLayout.LayoutParams contentLp =
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                );

        contentLp.gravity = Gravity.CENTER;

        root.addView(content, contentLp);

        /*
         * الشعار
         * نستعمل الأيقونة الجديدة نفسها داخل التطبيق
         */
        ImageView logo =
                new ImageView(this);

        logo.setImageResource(
                R.drawable.app_icon
        );

        logo.setScaleType(
                ImageView.ScaleType.CENTER_INSIDE
        );

        LinearLayout.LayoutParams logoLp =
                new LinearLayout.LayoutParams(
                        150,
                        150
                );

        logoLp.gravity =
                Gravity.CENTER_HORIZONTAL;

        content.addView(
                logo,
                logoLp
        );

        /*
         * اسم DATRIDHA
         */
        TextView brand =
                text(
                        "DATRIDHA",
                        34,
                        GOLD
                );

        brand.setTypeface(
                Typeface.create(
                        Typeface.SERIF,
                        Typeface.BOLD
                )
        );

        brand.setLetterSpacing(.16f);

        brand.setShadowLayer(
                8,
                0,
                3,
                Color.BLACK
        );

        content.addView(
                brand,
                new LinearLayout.LayoutParams(
                        -1,
                        65
                )
        );

        /*
         * العربية
         */
        TextView arabic =
                text(
                        "دَقْلَةُ النُّور مِنْ بَشْنِي",
                        22,
                        WHITE
                );

        arabic.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        arabic.setShadowLayer(
                6,
                0,
                2,
                Color.BLACK
        );

        content.addView(
                arabic,
                new LinearLayout.LayoutParams(
                        -1,
                        55
                )
        );

        /*
         * الفرنسية
         */
        TextView french =
                text(
                        "Deglet Nour de Bechni",
                        18,
                        WHITE
                );

        french.setTypeface(
                Typeface.create(
                        Typeface.SERIF,
                        Typeface.ITALIC
                )
        );

        french.setShadowLayer(
                5,
                0,
                2,
                Color.BLACK
        );

        content.addView(
                french,
                new LinearLayout.LayoutParams(
                        -1,
                        45
                )
        );

        TextView slogan =
                text(
                        "Achetez • Vendez • Échangez",
                        17,
                        WHITE
                );

        slogan.setShadowLayer(
                5,
                0,
                2,
                Color.BLACK
        );

        content.addView(
                slogan,
                new LinearLayout.LayoutParams(
                        -1,
                        45
                )
        );

        addSpace(content, 20);

        /*
         * زر ابدأ الآن — زر حقيقي
         */
        Button start =
                button("ابدأ الآن");

        start.setTextSize(19);

        start.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        start.setBackground(
                bg(GREEN, 55)
        );

        start.setOnClickListener(
                v -> showHome()
        );

        LinearLayout.LayoutParams startLp =
                new LinearLayout.LayoutParams(
                        270,
                        60
                );

        startLp.gravity =
                Gravity.CENTER_HORIZONTAL;

        content.addView(
                start,
                startLp
        );

        /*
         * اللغات
         */
        LinearLayout languages =
                new LinearLayout(this);

        languages.setGravity(
                Gravity.CENTER
        );

        languages.setPadding(
                0,
                12,
                0,
                0
        );

        Button ar =
                button("العربية");

        Button fr =
                button("Français");

        Button en =
                button("English");

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
                        100,
                        48
                )
        );

        languages.addView(
                fr,
                new LinearLayout.LayoutParams(
                        100,
                        48
                )
        );

        languages.addView(
                en,
                new LinearLayout.LayoutParams(
                        100,
                        48
                )
        );

        content.addView(languages);

        setContentView(root);
    }

    /* =========================================================
       HOME
       ========================================================= */

    private void showHome() {

        currentPage = "home";

        getWindow().getDecorView()
                .setSystemUiVisibility(0);

        LinearLayout root = baseLayout();

        addHeader(root, "DATRIDHA");

        TextView welcome = text(
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
                text(
                        "منصة تجارة دقلة النور من بشني",
                        17,
                        BROWN
                )
        );

        addSpace(root, 15);

        Button buy =
                button("🛒 شراء دقلة النور");

        buy.setOnClickListener(
                v -> showBuy()
        );

        root.addView(buy);

        Button sell =
                button("📦 بيع دقلة النور");

        sell.setOnClickListener(
                v -> showSell()
        );

        root.addView(sell);

        Button orders =
                button("📋 طلباتي");

        orders.setOnClickListener(
                v -> showOrders()
        );

        root.addView(orders);

        Button contact =
                button("📞 اتصل بنا");

        contact.setOnClickListener(
                v -> showContact()
        );

        root.addView(contact);

        Button settings =
                button("⚙ الإعدادات");

        settings.setOnClickListener(
                v -> showSettings()
        );

        root.addView(settings);

        setContentView(root);
    }

    /* =========================================================
       BUY
       ========================================================= */

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

        Button back =
                button("← العودة");

        back.setOnClickListener(
                v -> showHome()
        );

        root.addView(back);

        setContentView(root);
    }

    private void addProduct(
            LinearLayout root,
            String title,
            String description,
            String price) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                20,
                20,
                20,
                20
        );

        card.setBackground(
                bg(Color.WHITE, 30)
        );

        TextView t =
                text(
                        title,
                        21,
                        GREEN
                );

        t.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        card.addView(t);

        card.addView(
                text(
                        description,
                        16,
                        BROWN
                )
        );

        card.addView(
                text(
                        price,
                        17,
                        GOLD
                )
        );

        Button details =
                button("عرض التفاصيل");

        details.setOnClickListener(
                v -> showOfferDetails()
        );

        card.addView(details);

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        lp.setMargins(
                10,
                10,
                10,
                20
        );

        root.addView(
                card,
                lp
        );
    }

    /* =========================================================
       DETAILS
       ========================================================= */

    private void showOfferDetails() {

        currentPage = "details";

        LinearLayout root = baseLayout();

        addHeader(
                root,
                "تفاصيل العرض"
        );

        root.addView(
                text(
                        "دقلة النور من بشني",
                        25,
                        GREEN
                )
        );

        root.addView(
                text(
                        "تمر فاخر من واحات بشني – الفوار – قبلي",
                        17,
                        BROWN
                )
        );

        root.addView(
                text(
                        "تجارة مباشرة • جودة • ثقة",
                        17,
                        GOLD
                )
        );

        Button call =
                button(
                        "📞 الاتصال بالبائع"
                );

        call.setOnClickListener(
                v -> callPhone()
        );

        root.addView(call);

        Button order =
                button(
                        "🛒 طلب المنتج"
                );

        order.setOnClickListener(
                v -> Toast.makeText(
                        this,
                        "تم تسجيل طلبك",
                        Toast.LENGTH_SHORT
                ).show()
        );

        root.addView(order);

        Button back =
                button("← العودة");

        back.setOnClickListener(
                v -> showBuy()
        );

        root.addView(back);

        setContentView(root);
    }

    /* =========================================================
       SELL
       ========================================================= */

    private void showSell() {

        currentPage = "sell";

        LinearLayout root = baseLayout();

        addHeader(
                root,
                "بيع دقلة النور"
        );

        EditText quantity =
                new EditText(this);

        quantity.setHint(
                "الكمية بالكيلوغرام"
        );

        quantity.setInputType(
                android.text.InputType.TYPE_CLASS_NUMBER
        );

        root.addView(quantity);

        EditText price =
                new EditText(this);

        price.setHint(
                "السعر للكيلوغرام"
        );

        price.setInputType(
                android.text.InputType.TYPE_CLASS_NUMBER |
                android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        );

        root.addView(price);

        Button photo =
                button("📷 إضافة صورة");

        photo.setOnClickListener(
                v -> chooseImage()
        );

        root.addView(photo);

        Button publish =
                button("نشر العرض");

        publish.setOnClickListener(
                v -> Toast.makeText(
                        this,
                        "تم نشر العرض بنجاح",
                        Toast.LENGTH_LONG
                ).show()
        );

        root.addView(publish);

        Button back =
                button("← العودة");

        back.setOnClickListener(
                v -> showHome()
        );

        root.addView(back);

        setContentView(root);
    }

    /* =========================================================
       ORDERS
       ========================================================= */

    private void showOrders() {

        currentPage = "orders";

        LinearLayout root = baseLayout();

        addHeader(root, "طلباتي");

        root.addView(
                text(
                        "لا توجد طلبات حالياً",
                        20,
                        BROWN
                )
        );

        Button back =
                button("← العودة");

        back.setOnClickListener(
                v -> showHome()
        );

        root.addView(back);

        setContentView(root);
    }

    /* =========================================================
       CONTACT
       ========================================================= */

    private void showContact() {

        currentPage = "contact";

        LinearLayout root = baseLayout();

        addHeader(root, "اتصل بنا");

        root.addView(
                text(
                        "DATRIDHA",
                        30,
                        GOLD
                )
        );

        root.addView(
                text(
                        "دقلة النور من بشني",
                        21,
                        GREEN
                )
        );

        root.addView(
                text(
                        "للاستفسار والطلبات",
                        17,
                        BROWN
                )
        );

        Button phone =
                button(
                        "📞 +216 51022448"
                );

        phone.setOnClickListener(
                v -> callPhone()
        );

        root.addView(phone);

        Button email =
                button(
                        "✉ ridhatouil1992@gmail.com"
                );

        email.setOnClickListener(
                v -> sendEmail()
        );

        root.addView(email);

        Button back =
                button("← العودة");

        back.setOnClickListener(
                v -> showHome()
        );

        root.addView(back);

        setContentView(root);
    }

    /* =========================================================
       SETTINGS
       ========================================================= */

    private void showSettings() {

        currentPage = "settings";

        LinearLayout root = baseLayout();

        addHeader(
                root,
                "الإعدادات"
        );

        root.addView(
                text(
                        "اللغة",
                        20,
                        GREEN
                )
        );

        Button ar =
                button("العربية");

        Button fr =
                button("Français");

        Button en =
                button("English");

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

        root.addView(ar);
        root.addView(fr);
        root.addView(en);

        Button back =
                button("← العودة");

        back.setOnClickListener(
                v -> showHome()
        );

        root.addView(back);

        setContentView(root);
    }

    /* =========================================================
       HEADER
       ========================================================= */

    private void addHeader(
            LinearLayout root,
            String title) {

        TextView header =
                text(
                        title,
                        25,
                        WHITE
                );

        header.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        header.setBackground(
                bg(GREEN, 35)
        );

        header.setPadding(
                10,
                18,
                10,
                18
        );

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        lp.setMargins(
                0,
                0,
                0,
                20
        );

        root.addView(
                header,
                lp
        );
    }

    /* =========================================================
       BASE
       ========================================================= */

    private LinearLayout baseLayout() {

        ScrollView scroll =
                new ScrollView(this);

        LinearLayout content =
                new LinearLayout(this);

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setPadding(
                20,
                20,
                20,
                30
        );

        content.setBackgroundColor(
                CREAM
        );

        scroll.addView(content);

        setContentView(scroll);

        return content;
    }

    private void addSpace(
            LinearLayout root,
            int height) {

        Space s =
                new Space(this);

        root.addView(
                s,
                new LinearLayout.LayoutParams(
                        1,
                        height
                )
        );
    }

    /* =========================================================
       IMAGE PICKER
       ========================================================= */

    private void chooseImage() {

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

    /* =========================================================
       PHONE
       ========================================================= */

    private void callPhone() {

        Intent intent =
                new Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse(
                                "tel:+21651022448"
                        )
                );

        startActivity(intent);
    }

    /* =========================================================
       EMAIL
       ========================================================= */

    private void sendEmail() {

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
    }

    /* =========================================================
       BACK
       ========================================================= */

    @Override
    public void onBackPressed() {

        if (currentPage.equals("splash")) {

            super.onBackPressed();

        } else if (
                currentPage.equals("home")
        ) {

            showSplash();

        } else {

            showHome();
        }
    }
}
