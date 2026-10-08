package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import java.util.*;

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
       DATRIDHA LOGO DRAWN INSIDE THE APP
       D كبير + كعبة تمر ذهبية + غصن نخلة
       ========================================================= */

    private View createLuxuryLogo() {

        return new View(this) {

            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

            @Override
            protected void onDraw(Canvas c) {

                super.onDraw(c);

                float w = getWidth();
                float h = getHeight();

                p.setStrokeWidth(7);
                p.setStyle(Paint.Style.STROKE);
                p.setColor(GOLD);

                // حرف D كبير
                RectF d = new RectF(
                        w * .22f,
                        h * .20f,
                        w * .70f,
                        h * .82f
                );

                c.drawArc(d, -90, 180, false, p);

                c.drawLine(
                        w * .22f,
                        h * .20f,
                        w * .22f,
                        h * .82f,
                        p
                );

                // جذع النخلة
                p.setStrokeWidth(5);
                p.setColor(GOLD);

                Path trunk = new Path();
                trunk.moveTo(w * .48f, h * .43f);
                trunk.quadTo(
                        w * .47f,
                        h * .28f,
                        w * .53f,
                        h * .15f
                );

                c.drawPath(trunk, p);

                // سعف النخلة
                p.setStrokeWidth(3);

                for (int i = 0; i < 5; i++) {

                    Path leaf = new Path();

                    float x = w * .53f;
                    float y = h * .15f;

                    leaf.moveTo(x, y);

                    float ex = w * (.32f + i * .105f);
                    float ey = h * (.08f + Math.abs(i - 2) * .025f);

                    leaf.quadTo(
                            w * .50f,
                            h * .05f,
                            ex,
                            ey
                    );

                    c.drawPath(leaf, p);
                }

                // كعبة التمر الذهبية داخل D
                p.setStyle(Paint.Style.FILL);
                p.setColor(GOLD);

                c.drawOval(
                        new RectF(
                                w * .40f,
                                h * .43f,
                                w * .58f,
                                h * .63f
                        ),
                        p
                );

                // لمعان التمرة
                p.setColor(Color.rgb(255,235,150));

                c.drawOval(
                        new RectF(
                                w * .43f,
                                h * .46f,
                                w * .48f,
                                h * .56f
                        ),
                        p
                );
            }
        };
    }

    /* =========================================================
       SPLASH
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

        // خلفية كريمية فاخرة
        GradientDrawable background = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{
                        Color.rgb(249,244,236),
                        Color.rgb(238,226,197),
                        Color.rgb(225,207,160)
                }
        );

        root.setBackground(background);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER_HORIZONTAL);
        content.setPadding(28, 55, 28, 25);

        FrameLayout.LayoutParams cp =
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                );

        root.addView(content, cp);

        // شعار D الفاخر
        View logo = createLuxuryLogo();

        LinearLayout.LayoutParams lpLogo =
                new LinearLayout.LayoutParams(
                        230,
                        230
                );

        lpLogo.gravity = Gravity.CENTER_HORIZONTAL;

        content.addView(logo, lpLogo);

        // DATRIDHA
        TextView brand = text(
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

        content.addView(
                brand,
                new LinearLayout.LayoutParams(
                        -1,
                        65
                )
        );

        TextView arabic = text(
                "دَقْلَةُ النُّور مِنْ بَشْنِي",
                22,
                GREEN
        );

        arabic.setTypeface(
                Typeface.create(
                        Typeface.DEFAULT,
                        Typeface.BOLD
                )
        );

        content.addView(
                arabic,
                new LinearLayout.LayoutParams(
                        -1,
                        55
                )
        );

        TextView french = text(
                "Deglet Nour de Bechni",
                18,
                BROWN
        );

        french.setTypeface(
                Typeface.create(
                        Typeface.SERIF,
                        Typeface.ITALIC
                )
        );

        content.addView(
                french,
                new LinearLayout.LayoutParams(
                        -1,
                        45
                )
        );

        TextView slogan = text(
                "Achetez • Vendez • Échangez",
                17,
                BROWN
        );

        content.addView(
                slogan,
                new LinearLayout.LayoutParams(
                        -1,
                        45
                )
        );

        Space space = new Space(this);

        content.addView(
                space,
                new LinearLayout.LayoutParams(
                        1,
                        25
                )
        );

        // زر حقيقي
        Button start = button("ابدأ الآن");

        start.setTextSize(19);
        start.setTypeface(
                Typeface.create(
                        Typeface.DEFAULT,
                        Typeface.BOLD
                )
        );

        start.setBackground(bg(GREEN, 55));

        start.setOnClickListener(v -> showHome());

        LinearLayout.LayoutParams startLp =
                new LinearLayout.LayoutParams(
                        270,
                        60
                );

        startLp.gravity = Gravity.CENTER_HORIZONTAL;

        content.addView(start, startLp);

        // اللغات
        LinearLayout languages =
                new LinearLayout(this);

        languages.setGravity(Gravity.CENTER);
        languages.setPadding(0, 12, 0, 0);

        Button ar = button("العربية");
        Button fr = button("Français");
        Button en = button("English");

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

        languages.addView(ar,
                new LinearLayout.LayoutParams(
                        100, 48));

        languages.addView(fr,
                new LinearLayout.LayoutParams(
                        100, 48));

        languages.addView(en,
                new LinearLayout.LayoutParams(
                        100, 48));

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

        TextView subtitle = text(
                "منصة تجارة دقلة النور من بشني",
                17,
                BROWN
        );

        root.addView(subtitle);

        addSpace(root, 15);

        Button buy = button("🛒 شراء دقلة النور");
        buy.setOnClickListener(v -> showBuy());
        root.addView(buy);

        Button sell = button("📦 بيع دقلة النور");
        sell.setOnClickListener(v -> showSell());
        root.addView(sell);

        Button orders = button("📋 طلباتي");
        orders.setOnClickListener(v -> showOrders());
        root.addView(orders);

        Button contact = button("📞 اتصل بنا");
        contact.setOnClickListener(v -> showContact());
        root.addView(contact);

        Button settings = button("⚙ الإعدادات");
        settings.setOnClickListener(v -> showSettings());
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

        addProduct(root,
                "دقلة النور من بشني",
                "تمر فاخر • جودة عالية",
                "السعر حسب الكمية");

        addProduct(root,
                "دقلة نور ممتازة",
                "اختيار المنتج مباشرة من المصدر",
                "الدفع عند الاستلام");

        Button back = button("← العودة");
        back.setOnClickListener(v -> showHome());
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

        card.setPadding(20, 20, 20, 20);
        card.setBackground(bg(Color.WHITE, 30));

        TextView t = text(title, 21, GREEN);
        t.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        TextView d = text(
                description,
                16,
                BROWN
        );

        TextView p = text(
                price,
                17,
                GOLD
        );

        Button details =
                button("عرض التفاصيل");

        details.setOnClickListener(
                v -> showOfferDetails()
        );

        card.addView(t);
        card.addView(d);
        card.addView(p);
        card.addView(details);

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        lp.setMargins(10, 10, 10, 20);

        root.addView(card, lp);
    }

    /* =========================================================
       OFFER DETAILS
       ========================================================= */

    private void showOfferDetails() {

        currentPage = "details";

        LinearLayout root = baseLayout();

        addHeader(
                root,
                "تفاصيل العرض"
        );

        root.addView(text(
                "دقلة النور من بشني",
                25,
                GREEN
        ));

        root.addView(text(
                "تمر فاخر من واحات بشني – الفوار – قبلي",
                17,
                BROWN
        ));

        root.addView(text(
                "تجارة مباشرة • جودة • ثقة",
                17,
                GOLD
        ));

        Button call = button(
                "📞 الاتصال بالبائع"
        );

        call.setOnClickListener(
                v -> callPhone()
        );

        Button order = button(
                "🛒 طلب المنتج"
        );

        order.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "تم تسجيل طلبك",
                        Toast.LENGTH_SHORT
                ).show()
        );

        root.addView(call);
        root.addView(order);

        Button back = button("← العودة");
        back.setOnClickListener(v -> showBuy());

        root.addView(back);

        setContentView(root);
    }

    /* =========================================================
       SELL
       ========================================================= */

    private void showSell() {

        currentPage = "sell";

        LinearLayout root = baseLayout();

        addHeader(root, "بيع دقلة النور");

        EditText quantity =
                new EditText(this);

        quantity.setHint("الكمية بالكيلوغرام");
        quantity.setInputType(
                android.text.InputType.TYPE_CLASS_NUMBER
        );

        root.addView(quantity);

        EditText price =
                new EditText(this);

        price.setHint("السعر للكيلوغرام");
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

        publish.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "تم نشر العرض بنجاح",
                        Toast.LENGTH_LONG
                ).show()
        );

        root.addView(publish);

        Button back = button("← العودة");

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

        root.addView(text(
                "لا توجد طلبات حالياً",
                20,
                BROWN
        ));

        Button back = button("← العودة");

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

        root.addView(text(
                "DATRIDHA",
                30,
                GOLD
        ));

        root.addView(text(
                "دقلة النور من بشني",
                21,
                GREEN
        ));

        root.addView(text(
                "للاستفسار والطلبات",
                17,
                BROWN
        ));

        Button phone =
                button("📞 +216 51022448");

        phone.setOnClickListener(
                v -> callPhone()
        );

        root.addView(phone);

        Button email =
                button("✉ ridhatouil1992@gmail.com");

        email.setOnClickListener(
                v -> sendEmail()
        );

        root.addView(email);

        Button back = button("← العودة");

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

        addHeader(root, "الإعدادات");

        root.addView(text(
                "اللغة",
                20,
                GREEN
        ));

        Button ar = button("العربية");
        Button fr = button("Français");
        Button en = button("English");

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

        Button back = button("← العودة");

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
                text(title, 25, WHITE);

        header.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        header.setBackground(
                bg(GREEN, 35)
        );

        header.setPadding(
                10, 18, 10, 18
        );

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        lp.setMargins(0, 0, 0, 20);

        root.addView(header, lp);
    }

    /* =========================================================
       BASE LAYOUT
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
                20, 20, 20, 30
        );

        content.setBackgroundColor(CREAM);

        scroll.addView(content);

        setContentView(scroll);

        return content;
    }

    private void addSpace(
            LinearLayout root,
            int height) {

        Space s = new Space(this);

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
                        Uri.parse("tel:+21651022448")
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
        }
        else if (currentPage.equals("home")) {
            showSplash();
        }
        else {
            showHome();
        }
    }
}
