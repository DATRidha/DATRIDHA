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
import android.view.ViewGroup;
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
        showSplash();
    }

    // =====================================================
    // SPLASH - FULL SCREEN OASIS DESIGN
    // =====================================================

    private void showSplash() {

        currentPage = "splash";

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        FrameLayout root = new FrameLayout(this);

        // صورة الواحة - تغطي كامل الشاشة
        ImageView background = new ImageView(this);
        background.setImageResource(R.drawable.oasis_home);
        background.setScaleType(ImageView.ScaleType.CENTER_CROP);

        root.addView(
                background,
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        // طبقة شفافة خفيفة
        View overlay = new View(this);
        overlay.setBackgroundColor(
                Color.argb(60, 0, 0, 0)
        );

        root.addView(
                overlay,
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        // =================================================
        // LOGO + BRAND
        // =================================================

        LinearLayout brand = new LinearLayout(this);
        brand.setOrientation(LinearLayout.VERTICAL);
        brand.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.app_icon);
        logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        brand.addView(
                logo,
                new LinearLayout.LayoutParams(
                        dp(125),
                        dp(125)
                )
        );

        TextView title = new TextView(this);
        title.setText("DATRIDHA");
        title.setTextColor(WHITE);
        title.setTextSize(30);
        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );
        title.setGravity(Gravity.CENTER);

        brand.addView(title);

        TextView subtitle = new TextView(this);

        subtitle.setText(
                language.equals("fr")
                        ? "Deglet Nour de Bechni\nCommerce direct du producteur à l'acheteur"
                        : language.equals("en")
                        ? "Deglet Nour dates from Bechni\nDirect trade from producer to buyer"
                        : "دقلة النور من بشني\nتجارة مباشرة من المنتج إلى المشتري"
        );

        subtitle.setTextColor(WHITE);
        subtitle.setTextSize(17);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setTypeface(
                Typeface.DEFAULT,
                Typeface.NORMAL
        );

        brand.addView(subtitle);

        FrameLayout.LayoutParams brandParams =
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        brandParams.gravity =
                Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        brandParams.topMargin = dp(65);

        root.addView(
                brand,
                brandParams
        );

        // =================================================
        // START BUTTON
        // =================================================

        Button startButton = new Button(this);

        startButton.setText(
                language.equals("fr")
                        ? "Commencer"
                        : language.equals("en")
                        ? "Start"
                        : "ابدأ الآن"
        );

        startButton.setTextSize(18);
        startButton.setTextColor(WHITE);
        startButton.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );
        startButton.setAllCaps(false);

        GradientDrawable startBackground =
                new GradientDrawable();

        startBackground.setColor(GREEN);
        startBackground.setCornerRadius(
                dp(35)
        );
        startBackground.setStroke(
                dp(2),
                GOLD
        );

        startButton.setBackground(
                startBackground
        );

        startButton.setOnClickListener(
                v -> showHome()
        );

        FrameLayout.LayoutParams startParams =
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dp(60)
                );

        startParams.gravity =
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        startParams.setMargins(
                dp(45),
                0,
                dp(45),
                dp(90)
        );

        root.addView(
                startButton,
                startParams
        );

        // =================================================
        // LANGUAGE BUTTONS
        // =================================================

        LinearLayout languages =
                new LinearLayout(this);

        languages.setOrientation(
                LinearLayout.HORIZONTAL
        );

        languages.setGravity(
                Gravity.CENTER
        );

        Button ar = splashLanguageButton(
                "العربية"
        );

        Button fr = splashLanguageButton(
                "Français"
        );

        Button en = splashLanguageButton(
                "English"
        );

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

        languages.addView(ar);
        languages.addView(fr);
        languages.addView(en);

        FrameLayout.LayoutParams languageParams =
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        languageParams.gravity =
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        languageParams.bottomMargin = dp(15);

        root.addView(
                languages,
                languageParams
        );

        setContentView(root);
    }

    // =====================================================
    // SPLASH LANGUAGE BUTTON
    // =====================================================

    private Button splashLanguageButton(
            String label
    ) {

        Button b = new Button(this);

        b.setText(label);
        b.setTextColor(WHITE);
        b.setTextSize(13);
        b.setAllCaps(false);
        b.setBackgroundColor(
                Color.TRANSPARENT
        );

        return b;
    }

    // =====================================================
    // HOME
    // =====================================================

    private void showHome() {

        currentPage = "home";

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

        LinearLayout root = baseLayout();

        addHeader(root);

        root.addView(title(
                language.equals("fr")
                        ? "Bienvenue sur DATRIDHA"
                        : language.equals("en")
                        ? "Welcome to DATRIDHA"
                        : "مرحباً بك في DATRIDHA"
        ));

        TextView description = text(
                language.equals("fr")
                        ? "Commerce direct de Deglet Nour"
                        : language.equals("en")
                        ? "Direct Deglet Nour dates marketplace"
                        : "منصة مباشرة لبيع وشراء دقلة النور",
                16
        );

        description.setGravity(Gravity.CENTER);

        root.addView(description);

        space(root, 20);

        Button buy = button(
                language.equals("fr")
                        ? "Acheter des dattes"
                        : language.equals("en")
                        ? "Buy dates"
                        : "شراء التمور"
        );

        buy.setOnClickListener(
                v -> showBuy()
        );

        root.addView(buy);

        Button sell = button(
                language.equals("fr")
                        ? "Vendre mes dattes"
                        : language.equals("en")
                        ? "Sell my dates"
                        : "بيع التمور"
        );

        sell.setOnClickListener(
                v -> showSell()
        );

        root.addView(sell);

        Button orders = button(
                language.equals("fr")
                        ? "Mes commandes"
                        : language.equals("en")
                        ? "My orders"
                        : "طلباتي"
        );

        orders.setOnClickListener(
                v -> showOrders()
        );

        root.addView(orders);

        Button contact = button(
                language.equals("fr")
                        ? "Contact"
                        : language.equals("en")
                        ? "Contact"
                        : "الاتصال بنا"
        );

        contact.setOnClickListener(
                v -> showContact()
        );

        root.addView(contact);

        Button settings = button(
                language.equals("fr")
                        ? "Paramètres"
                        : language.equals("en")
                        ? "Settings"
                        : "الإعدادات"
        );

        settings.setOnClickListener(
                v -> showSettings()
        );

        root.addView(settings);
    }

    // =====================================================
    // BUY
    // =====================================================

    private void showBuy() {

        currentPage = "buy";

        LinearLayout root = baseLayout();

        addBackButton(root);
        addHeader(root);

        root.addView(title(
                language.equals("fr")
                        ? "Acheter des dattes"
                        : language.equals("en")
                        ? "Buy dates"
                        : "شراء دقلة النور"
        ));

        addProduct(
                root,
                "Deglet Nour – Qualité Premium",
                "دقلة نور فاخرة من بشني",
                "25 kg",
                "Prix sur demande"
        );

        addProduct(
                root,
                "Deglet Nour – Qualité Standard",
                "دقلة نور أصلية من المنتج",
                "10 kg",
                "Prix sur demande"
        );

        addProduct(
                root,
                "Deglet Nour – Gros volume",
                "طلبات الجملة والكميات الكبيرة",
                "50 kg +",
                "Prix sur demande"
        );
    }

    private void addProduct(
            LinearLayout root,
            String name,
            String arabic,
            String quantity,
            String price
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                dp(18),
                dp(15),
                dp(18),
                dp(15)
        );

        card.setGravity(
                Gravity.CENTER
        );

        TextView n = text(
                name,
                19
        );

        n.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        n.setTextColor(GREEN);
        n.setGravity(Gravity.CENTER);

        card.addView(n);

        TextView a = text(
                arabic,
                16
        );

        a.setGravity(Gravity.CENTER);

        card.addView(a);

        TextView q = text(
                quantity + "   •   " + price,
                15
        );

        q.setGravity(Gravity.CENTER);

        card.addView(q);

        Button details = button(
                language.equals("fr")
                        ? "Voir l'offre"
                        : language.equals("en")
                        ? "View offer"
                        : "عرض التفاصيل"
        );

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
                dp(10),
                dp(8),
                dp(10),
                dp(8)
        );

        root.addView(
                card,
                lp
        );
    }

    // =====================================================
    // DETAILS
    // =====================================================

    private void showOfferDetails() {

        currentPage = "details";

        LinearLayout root = baseLayout();

        addBackButton(root);
        addHeader(root);

        root.addView(title(
                language.equals("fr")
                        ? "Détails de l'offre"
                        : language.equals("en")
                        ? "Offer details"
                        : "تفاصيل العرض"
        ));

        TextView product = text(
                "Déglet Nour – Bechni\n\n" +
                "دقلة النور من بشني\n\n" +
                "Qualité : Premium\n" +
                "Origine : Bechni – El Fawar – Kebili\n" +
                "Commerce direct producteur / acheteur",
                18
        );

        product.setGravity(
                Gravity.CENTER
        );

        root.addView(product);

        space(root, 20);

        Button call = button(
                language.equals("fr")
                        ? "Appeler le vendeur"
                        : language.equals("en")
                        ? "Call seller"
                        : "الاتصال بالبائع"
        );

        call.setOnClickListener(
                v -> callPhone()
        );

        root.addView(call);

        Button order = button(
                language.equals("fr")
                        ? "Demander cette offre"
                        : language.equals("en")
                        ? "Request this offer"
                        : "طلب هذا العرض"
        );

        order.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        language.equals("fr")
                                ? "Demande envoyée"
                                : language.equals("en")
                                ? "Request sent"
                                : "تم إرسال الطلب",
                        Toast.LENGTH_SHORT
                ).show()
        );

        root.addView(order);
    }

    // =====================================================
    // SELL
    // =====================================================

    private void showSell() {

        currentPage = "sell";

        LinearLayout root = baseLayout();

        addBackButton(root);
        addHeader(root);

        root.addView(title(
                language.equals("fr")
                        ? "Vendre mes dattes"
                        : language.equals("en")
                        ? "Sell my dates"
                        : "بيع التمور"
        ));

        root.addView(text(
                language.equals("fr")
                        ? "Publiez votre offre de Deglet Nour."
                        : language.equals("en")
                        ? "Publish your Deglet Nour offer."
                        : "انشر عرضك لبيع دقلة النور.",
                17
        ));

        EditText quantity = input(
                language.equals("fr")
                        ? "Quantité en kg"
                        : language.equals("en")
                        ? "Quantity in kg"
                        : "الكمية بالكيلوغرام"
        );

        root.addView(quantity);

        EditText price = input(
                language.equals("fr")
                        ? "Prix par kg"
                        : language.equals("en")
                        ? "Price per kg"
                        : "السعر للكيلوغرام"
        );

        root.addView(price);

        Button photo = button(
                language.equals("fr")
                        ? "Ajouter une photo"
                        : language.equals("en")
                        ? "Add photo"
                        : "إضافة صورة"
        );

        photo.setOnClickListener(
                v -> chooseImage()
        );

        root.addView(photo);

        Button publish = button(
                language.equals("fr")
                        ? "Publier l'offre"
                        : language.equals("en")
                        ? "Publish offer"
                        : "نشر العرض"
        );

        publish.setOnClickListener(v -> {

            if (
                    quantity.getText()
                            .toString()
                            .trim()
                            .isEmpty()
            ) {

                quantity.setError(
                        language.equals("fr")
                                ? "Entrez la quantité"
                                : language.equals("en")
                                ? "Enter quantity"
                                : "أدخل الكمية"
                );

                return;
            }

            Toast.makeText(
                    this,
                    language.equals("fr")
                            ? "Offre publiée"
                            : language.equals("en")
                            ? "Offer published"
                            : "تم نشر العرض بنجاح",
                    Toast.LENGTH_SHORT
            ).show();
        });

        root.addView(publish);
    }

    // =====================================================
    // ORDERS
    // =====================================================

    private void showOrders() {

        currentPage = "orders";

        LinearLayout root = baseLayout();

        addBackButton(root);
        addHeader(root);

        root.addView(title(
                language.equals("fr")
                        ? "Mes commandes"
                        : language.equals("en")
                        ? "My orders"
                        : "طلباتي"
        ));

        TextView empty = text(
                language.equals("fr")
                        ? "Aucune commande pour le moment."
                        : language.equals("en")
                        ? "No orders yet."
                        : "لا توجد طلبات حالياً.",
                18
        );

        empty.setGravity(
                Gravity.CENTER
        );

        root.addView(empty);
    }

    // =====================================================
    // CONTACT
    // =====================================================

    private void showContact() {

        currentPage = "contact";

        LinearLayout root = baseLayout();

        addBackButton(root);
        addHeader(root);

        root.addView(title(
                language.equals("fr")
                        ? "Contactez-nous"
                        : language.equals("en")
                        ? "Contact us"
                        : "اتصل بنا"
        ));

        root.addView(text(
                "DATRIDHA\n\n" +
                "دقلة النور من بشني\n\n" +
                "Téléphone : +216 51022448\n" +
                "Email : ridhatouil1992@gmail.com",
                18
        ));

        Button phone = button(
                language.equals("fr")
                        ? "Appeler"
                        : language.equals("en")
                        ? "Call"
                        : "اتصال هاتفي"
        );

        phone.setOnClickListener(
                v -> callPhone()
        );

        root.addView(phone);

        Button email = button(
                language.equals("fr")
                        ? "Envoyer un email"
                        : language.equals("en")
                        ? "Send email"
                        : "إرسال بريد إلكتروني"
        );

        email.setOnClickListener(
                v -> sendEmail()
        );

        root.addView(email);
    }

    // =====================================================
    // SETTINGS
    // =====================================================

    private void showSettings() {

        currentPage = "settings";

        LinearLayout root = baseLayout();

        addBackButton(root);
        addHeader(root);

        root.addView(title(
                language.equals("fr")
                        ? "Paramètres"
                        : language.equals("en")
                        ? "Settings"
                        : "الإعدادات"
        ));

        Button languageButton = button(
                language.equals("fr")
                        ? "Changer la langue"
                        : language.equals("en")
                        ? "Change language"
                        : "تغيير اللغة"
        );

        languageButton.setOnClickListener(
                v -> changeLanguage()
        );

        root.addView(languageButton);

        Button notifications = button(
                language.equals("fr")
                        ? "Notifications"
                        : language.equals("en")
                        ? "Notifications"
                        : "الإشعارات"
        );

        notifications.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        language.equals("fr")
                                ? "Notifications activées"
                                : language.equals("en")
                                ? "Notifications enabled"
                                : "الإشعارات مفعلة",
                        Toast.LENGTH_SHORT
                ).show()
        );

        root.addView(notifications);

        Button about = button(
                language.equals("fr")
                        ? "À propos de DATRIDHA"
                        : language.equals("en")
                        ? "About DATRIDHA"
                        : "حول DATRIDHA"
        );

        about.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "DATRIDHA – Deglet Nour de Bechni",
                        Toast.LENGTH_LONG
                ).show()
        );

        root.addView(about);

        Button help = button(
                language.equals("fr")
                        ? "Aide"
                        : language.equals("en")
                        ? "Help"
                        : "المساعدة"
        );

        help.setOnClickListener(
                v -> showContact()
        );

        root.addView(help);
    }

    // =====================================================
    // LANGUAGE
    // =====================================================

    private void changeLanguage() {

        if ("ar".equals(language)) {

            language = "fr";

        } else if ("fr".equals(language)) {

            language = "en";

        } else {

            language = "ar";
        }

        showSettings();
    }

    // =====================================================
    // HEADER
    // =====================================================

    private void addHeader(
            LinearLayout root
    ) {

        ImageView logo =
                new ImageView(this);

        logo.setImageResource(
                R.drawable.app_icon
        );

        logo.setScaleType(
                ImageView.ScaleType.CENTER_INSIDE
        );

        root.addView(
                logo,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(100)
                )
        );

        View line =
                new View(this);

        line.setBackgroundColor(GOLD);

        root.addView(
                line,
                new LinearLayout.LayoutParams(
                        dp(180),
                        dp(2)
                )
        );

        space(root, 10);
    }

    // =====================================================
    // BACK
    // =====================================================

    private void addBackButton(
            LinearLayout root
    ) {

        Button back = smallButton(
                language.equals("fr")
                        ? "← Retour"
                        : language.equals("en")
                        ? "← Back"
                        : "← رجوع"
        );

        back.setOnClickListener(v -> {

            if ("details".equals(currentPage)) {

                showBuy();

            } else {

                showHome();
            }
        });

        root.addView(back);
    }

    // =====================================================
    // BASE LAYOUT
    // =====================================================

    private LinearLayout baseLayout() {

        ScrollView scroll =
                new ScrollView(this);

        scroll.setFillViewport(true);

        LinearLayout root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        root.setPadding(
                dp(18),
                dp(20),
                dp(18),
                dp(20)
        );

        root.setBackgroundColor(
                CREAM
        );

        scroll.addView(root);

        setContentView(scroll);

        return root;
    }

    // =====================================================
    // BUTTON
    // =====================================================

    private Button button(
            String label
    ) {

        Button b =
                new Button(this);

        b.setText(label);
        b.setTextSize(17);
        b.setTextColor(WHITE);

        b.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(GREEN);
        bg.setCornerRadius(dp(14));
        bg.setStroke(dp(1), GOLD);

        b.setBackground(bg);

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(58)
                );

        lp.setMargins(
                dp(5),
                dp(7),
                dp(5),
                dp(7)
        );

        b.setLayoutParams(lp);

        return b;
    }

    // =====================================================
    // SMALL BUTTON
    // =====================================================

    private Button smallButton(
            String label
    ) {

        Button b =
                new Button(this);

        b.setText(label);
        b.setTextSize(14);
        b.setTextColor(GREEN);

        b.setAllCaps(false);

        b.setBackgroundColor(
                Color.TRANSPARENT
        );

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        -2,
                        dp(50)
                );

        lp.setMargins(
                dp(3),
                dp(3),
                dp(3),
                dp(3)
        );

        b.setLayoutParams(lp);

        return b;
    }

    // =====================================================
    // TITLE
    // =====================================================

    private TextView title(
            String value
    ) {

        TextView t =
                text(value, 25);

        t.setTextColor(BROWN);

        t.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        t.setGravity(
                Gravity.CENTER
        );

        t.setPadding(
                dp(5),
                dp(8),
                dp(5),
                dp(8)
        );

        return t;
    }

    // =====================================================
    // TEXT
    // =====================================================

    private TextView text(
            String value,
            int size
    ) {

        TextView t =
                new TextView(this);

        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(BROWN);

        t.setGravity(
                Gravity.CENTER_VERTICAL
        );

        t.setPadding(
                dp(5),
                dp(8),
                dp(5),
                dp(8)
        );

        return t;
    }

    // =====================================================
    // INPUT
    // =====================================================

    private EditText input(
            String hint
    ) {

        EditText e =
                new EditText(this);

        e.setHint(hint);
        e.setTextSize(16);
        e.setSingleLine(true);

        e.setPadding(
                dp(15),
                dp(10),
                dp(15),
                dp(10)
        );

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        -1,
                        dp(58)
                );

        lp.setMargins(
                dp(5),
                dp(8),
                dp(5),
                dp(8)
        );

        e.setLayoutParams(lp);

        return e;
    }

    // =====================================================
    // SPACE
    // =====================================================

    private void space(
            LinearLayout root,
            int size
    ) {

        Space s =
                new Space(this);

        root.addView(
                s,
                new LinearLayout.LayoutParams(
                        1,
                        dp(size)
                )
        );
    }

    // =====================================================
    // IMAGE PICKER
    // =====================================================

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

        if (
                requestCode == 100 &&
                resultCode == RESULT_OK
        ) {

            Toast.makeText(
                    this,
                    language.equals("fr")
                            ? "Image sélectionnée"
                            : language.equals("en")
                            ? "Image selected"
                            : "تم اختيار الصورة",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =====================================================
    // PHONE
    // =====================================================

    private void callPhone() {

        try {

            Intent intent =
                    new Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse(
                                    "tel:+21651022448"
                            )
                    );

            startActivity(intent);

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "+216 51022448",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =====================================================
    // EMAIL
    // =====================================================

    private void sendEmail() {

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
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    // =====================================================
    // DP
    // =====================================================

    private int dp(
            int value
    ) {

        return (int) (
                value *
                        getResources()
                                .getDisplayMetrics()
                                .density
        );
    }

    // =====================================================
    // BACK NAVIGATION
    // =====================================================

    @Override
    public void onBackPressed() {

        if ("details".equals(currentPage)) {

            showBuy();

        } else if (
                "buy".equals(currentPage) ||
                "sell".equals(currentPage) ||
                "orders".equals(currentPage) ||
                "contact".equals(currentPage) ||
                "settings".equals(currentPage)
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
