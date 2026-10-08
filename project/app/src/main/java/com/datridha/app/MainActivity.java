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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(GREEN);
        getWindow().setNavigationBarColor(GREEN);

        showSplash();
    }

    private GradientDrawable roundedBackground(int color, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    private TextView makeText(String value, float size, int color) {
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

    /*
     * =========================================================
     * شاشة البداية
     * =========================================================
     */

    private void showSplash() {

        currentPage = "splash";

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        FrameLayout root = new FrameLayout(this);

        /*
         * الخلفية فقط.
         * ليست أزراراً وليست واجهة كاملة.
         */
        ImageView background = new ImageView(this);
        background.setImageResource(R.drawable.splash_oasis);
        background.setScaleType(ImageView.ScaleType.CENTER_CROP);

        root.addView(
                background,
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                )
        );

        /*
         * طبقة شفافة لتحسين وضوح الكتابة.
         */
        View shade = new View(this);

        GradientDrawable shadeDrawable =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{
                                0x18000000,
                                0x45000000,
                                0x88000000
                        }
                );

        shade.setBackground(shadeDrawable);

        root.addView(
                shade,
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                )
        );

        /*
         * المحتوى الحقيقي.
         */
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER_HORIZONTAL);
        content.setPadding(25, 45, 25, 25);

        FrameLayout.LayoutParams contentParams =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                );

        contentParams.gravity = Gravity.CENTER;

        root.addView(content, contentParams);

        /*
         * الشعار
         */
        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.app_icon);
        logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        LinearLayout.LayoutParams logoParams =
                new LinearLayout.LayoutParams(135, 135);

        logoParams.gravity = Gravity.CENTER_HORIZONTAL;

        content.addView(logo, logoParams);

        /*
         * اسم التطبيق
         */
        TextView brand =
                makeText("DATRIDHA", 32, GOLD);

        brand.setTypeface(
                Typeface.create(Typeface.SERIF, Typeface.BOLD)
        );

        brand.setLetterSpacing(0.12f);
        brand.setShadowLayer(7, 0, 3, Color.BLACK);

        content.addView(
                brand,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        60
                )
        );

        /*
         * العربية
         */
        TextView arabic =
                makeText(
                        "دَقْلَةُ النُّور مِنْ بَشْنِي",
                        21,
                        WHITE
                );

        arabic.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        arabic.setShadowLayer(6, 0, 2, Color.BLACK);

        content.addView(
                arabic,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        50
                )
        );

        /*
         * الفرنسية
         */
        TextView french =
                makeText(
                        "Deglet Nour de Bechni",
                        17,
                        WHITE
                );

        french.setTypeface(
                Typeface.create(Typeface.SERIF, Typeface.ITALIC)
        );

        french.setShadowLayer(5, 0, 2, Color.BLACK);

        content.addView(
                french,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        45
                )
        );

        /*
         * الشعار
         */
        TextView slogan =
                makeText(
                        "Achetez • Vendez • Échangez",
                        16,
                        WHITE
                );

        slogan.setShadowLayer(5, 0, 2, Color.BLACK);

        content.addView(
                slogan,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        45
                )
        );

        addSpace(content, 18);

        /*
         * زر ابدأ الحقيقي.
         *
         * مهم:
         * لا يوجد هنا أي رابط خارجي.
         * الزر يستدعي showHome() مباشرة داخل التطبيق.
         */
        Button startButton =
                makeButton("ابدأ الآن");

        startButton.setTextSize(19);
        startButton.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        startButton.setBackground(
                roundedBackground(GREEN, 55)
        );

        startButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentPage = "home";
                        showHome();
                    }
                }
        );

        LinearLayout.LayoutParams startParams =
                new LinearLayout.LayoutParams(
                        270,
                        60
                );

        startParams.gravity = Gravity.CENTER_HORIZONTAL;

        content.addView(startButton, startParams);

        addSpace(content, 12);

        /*
         * اللغات
         */
        LinearLayout languages = new LinearLayout(this);
        languages.setOrientation(LinearLayout.HORIZONTAL);
        languages.setGravity(Gravity.CENTER);

        Button ar = makeButton("العربية");
        Button fr = makeButton("Français");
        Button en = makeButton("English");

        ar.setOnClickListener(
                v -> {
                    language = "ar";
                    showSplash();
                }
        );

        fr.setOnClickListener(
                v -> {
                    language = "fr";
                    showSplash();
                }
        );

        en.setOnClickListener(
                v -> {
                    language = "en";
                    showSplash();
                }
        );

        languages.addView(
                ar,
                new LinearLayout.LayoutParams(100, 48)
        );

        languages.addView(
                fr,
                new LinearLayout.LayoutParams(100, 48)
        );

        languages.addView(
                en,
                new LinearLayout.LayoutParams(100, 48)
        );

        content.addView(languages);

        setContentView(root);
    }

    /*
     * =========================================================
     * الصفحة الرئيسية
     * =========================================================
     */

    private void showHome() {

        currentPage = "home";

        getWindow().getDecorView().setSystemUiVisibility(0);

        LinearLayout root = baseLayout();

        addHeader(root, "DATRIDHA");

        TextView welcome =
                makeText(
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

        Button buy =
                makeButton("🛒 شراء دقلة النور");

        buy.setOnClickListener(
                v -> showBuy()
        );

        root.addView(buy);

        Button sell =
                makeButton("📦 بيع دقلة النور");

        sell.setOnClickListener(
                v -> showSell()
        );

        root.addView(sell);

        Button orders =
                makeButton("📋 طلباتي");

        orders.setOnClickListener(
                v -> showOrders()
        );

        root.addView(orders);

        Button contact =
                makeButton("📞 اتصل بنا");

        contact.setOnClickListener(
                v -> showContact()
        );

        root.addView(contact);

        Button settings =
                makeButton("⚙ الإعدادات");

        settings.setOnClickListener(
                v -> showSettings()
        );

        root.addView(settings);

        setContentView(root);
    }

    /*
     * =========================================================
     * شراء
     * =========================================================
     */

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
                makeButton("← العودة");

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
            String price
    ) {

        LinearLayout card = new LinearLayout(this);

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
                roundedBackground(
                        Color.WHITE,
                        30
                )
        );

        TextView titleText =
                makeText(
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
                makeText(
                        description,
                        16,
                        BROWN
                )
        );

        card.addView(
                makeText(
                        price,
                        17,
                        GOLD
                )
        );

        Button details =
                makeButton("عرض التفاصيل");

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

    /*
     * =========================================================
     * تفاصيل العرض
     * =========================================================
     */

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

        Button call =
                makeButton("📞 الاتصال بالبائع");

        call.setOnClickListener(
                v -> callPhone()
        );

        root.addView(call);

        Button order =
                makeButton("🛒 طلب المنتج");

        order.setOnClickListener(
                v -> Toast.makeText(
                        this,
                        "تم تسجيل طلبك",
                        Toast.LENGTH_SHORT
                ).show()
        );

        root.addView(order);

        Button back =
                makeButton("← العودة");

        back.setOnClickListener(
                v -> showBuy()
        );

        root.addView(back);

        setContentView(root);
    }

    /*
     * =========================================================
     * بيع
     * =========================================================
     */

    private void showSell() {

        currentPage = "sell";

        LinearLayout root = baseLayout();

        addHeader(root, "بيع دقلة النور");

        EditText quantity = new EditText(this);

        quantity.setHint("الكمية بالكيلوغرام");

        quantity.setInputType(
                android.text.InputType.TYPE_CLASS_NUMBER
        );

        root.addView(quantity);

        EditText price = new EditText(this);

        price.setHint("السعر للكيلوغرام");

        price.setInputType(
                android.text.InputType.TYPE_CLASS_NUMBER
                        | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        );

        root.addView(price);

        Button photo =
                makeButton("📷 إضافة صورة");

        photo.setOnClickListener(
                v -> chooseImage()
        );

        root.addView(photo);

        Button publish =
                makeButton("نشر العرض");

        publish.setOnClickListener(
                v -> Toast.makeText(
                        this,
                        "تم نشر العرض بنجاح",
                        Toast.LENGTH_LONG
                ).show()
        );

        root.addView(publish);

        Button back =
                makeButton("← العودة");

        back.setOnClickListener(
                v -> showHome()
        );

        root.addView(back);

        setContentView(root);
    }

    /*
     * =========================================================
     * الطلبات
     * =========================================================
     */

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

        Button back =
                makeButton("← العودة");

        back.setOnClickListener(
                v -> showHome()
        );

        root.addView(back);

        setContentView(root);
    }

    /*
     * =========================================================
     * الاتصال
     * =========================================================
     */

    private void showContact() {

        currentPage = "contact";

        LinearLayout root = baseLayout();

        addHeader(root, "اتصل بنا");

        root.addView(
                makeText(
                        "DATRIDHA",
                        30,
                        GOLD
                )
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

        Button phone =
                makeButton("📞 +216 51022448");

        phone.setOnClickListener(
                v -> callPhone()
        );

        root.addView(phone);

        Button email =
                makeButton(
                        "✉ ridhatouil1992@gmail.com"
                );

        email.setOnClickListener(
                v -> sendEmail()
        );

        root.addView(email);

        Button back =
                makeButton("← العودة");

        back.setOnClickListener(
                v -> showHome()
        );

        root.addView(back);

        setContentView(root);
    }

    /*
     * =========================================================
     * الإعدادات
     * =========================================================
     */

    private void showSettings() {

        currentPage = "settings";

        LinearLayout root = baseLayout();

        addHeader(root, "الإعدادات");

        root.addView(
                makeText(
                        "اللغة",
                        20,
                        GREEN
                )
        );

        Button ar =
                makeButton("العربية");

        Button fr =
                makeButton("Français");

        Button en =
                makeButton("English");

        ar.setOnClickListener(
                v -> {
                    language = "ar";
                    showSettings();
                }
        );

        fr.setOnClickListener(
                v -> {
                    language = "fr";
                    showSettings();
                }
        );

        en.setOnClickListener(
                v -> {
                    language = "en";
                    showSettings();
                }
        );

        root.addView(ar);
        root.addView(fr);
        root.addView(en);

        Button back =
                makeButton("← العودة");

        back.setOnClickListener(
                v -> showHome()
        );

        root.addView(back);

        setContentView(root);
    }

    /*
     * =========================================================
     * الأدوات المساعدة
     * =========================================================
     */

    private void addHeader(
            LinearLayout root,
            String title
    ) {

        TextView header =
                makeText(
                        title,
                        25,
                        WHITE
                );

        header.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        header.setBackground(
                roundedBackground(
                        GREEN,
                        35
                )
        );

        header.setPadding(
                10,
                18,
                10,
                18
        );

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(
                0,
                0,
                0,
                20
        );

        root.addView(header, params);
    }

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

        content.setBackgroundColor(CREAM);

        scroll.addView(content);

        setContentView(scroll);

        return content;
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

    private void callPhone() {

        Intent intent =
                new Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:+21651022448")
                );

        startActivity(intent);
    }

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
