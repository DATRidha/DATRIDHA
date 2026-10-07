package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
        showSplash();
    }

    // =====================================================
    // SPLASH
    // =====================================================

    private void showSplash() {

        currentPage = "splash";

        LinearLayout root = baseLayout();

        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.logo_datridha);
        logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        root.addView(logo, new LinearLayout.LayoutParams(
                -1, dp(180)
        ));

        TextView title = title("DATRIDHA");
        root.addView(title);

        TextView subtitle = text(
                "دقلة النور من بشني\nتجارة مباشرة من المنتج إلى المشتري",
                18
        );
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle);

        space(root, 25);

        Button start = button(
                language.equals("fr") ? "Commencer" :
                language.equals("en") ? "Start" : "ابدأ الآن"
        );

        start.setOnClickListener(v -> showHome());
        root.addView(start);

        space(root, 15);

        TextView languageTitle = text(
                language.equals("fr") ? "Choisir la langue" :
                language.equals("en") ? "Choose language" :
                "اختر اللغة",
                15
        );
        languageTitle.setGravity(Gravity.CENTER);
        root.addView(languageTitle);

        LinearLayout languages = new LinearLayout(this);
        languages.setOrientation(LinearLayout.HORIZONTAL);
        languages.setGravity(Gravity.CENTER);

        Button ar = smallButton("العربية");
        Button fr = smallButton("Français");
        Button en = smallButton("English");

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

        root.addView(languages);

        setContentView(root);
    }

    // =====================================================
    // HOME
    // =====================================================

    private void showHome() {

        currentPage = "home";

        LinearLayout root = baseLayout();

        addHeader(root);

        TextView welcome = title(
                language.equals("fr") ? "Bienvenue sur DATRIDHA" :
                language.equals("en") ? "Welcome to DATRIDHA" :
                "مرحباً بك في DATRIDHA"
        );

        root.addView(welcome);

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
                language.equals("fr") ? "Acheter des dattes" :
                language.equals("en") ? "Buy dates" :
                "شراء التمور"
        );

        buy.setOnClickListener(v -> showBuy());
        root.addView(buy);

        Button sell = button(
                language.equals("fr") ? "Vendre mes dattes" :
                language.equals("en") ? "Sell my dates" :
                "بيع التمور"
        );

        sell.setOnClickListener(v -> showSell());
        root.addView(sell);

        Button orders = button(
                language.equals("fr") ? "Mes commandes" :
                language.equals("en") ? "My orders" :
                "طلباتي"
        );

        orders.setOnClickListener(v -> showOrders());
        root.addView(orders);

        Button contact = button(
                language.equals("fr") ? "Contact" :
                language.equals("en") ? "Contact" :
                "الاتصال بنا"
        );

        contact.setOnClickListener(v -> showContact());
        root.addView(contact);

        Button settings = button(
                language.equals("fr") ? "Paramètres" :
                language.equals("en") ? "Settings" :
                "الإعدادات"
        );

        settings.setOnClickListener(v -> showSettings());
        root.addView(settings);

        setContentView(root);
    }

    // =====================================================
    // BUY
    // =====================================================

    private void showBuy() {

        currentPage = "buy";

        LinearLayout root = baseLayout();

        addBackButton(root);

        addHeader(root);

        TextView heading = title(
                language.equals("fr") ? "Acheter des dattes" :
                language.equals("en") ? "Buy dates" :
                "شراء دقلة النور"
        );

        root.addView(heading);

        addProduct(root,
                "Deglet Nour – Qualité Premium",
                "دقلة نور فاخرة من بشني",
                "25 kg",
                "Prix sur demande"
        );

        addProduct(root,
                "Deglet Nour – Qualité Standard",
                "دقلة نور أصلية من المنتج",
                "10 kg",
                "Prix sur demande"
        );

        addProduct(root,
                "Deglet Nour – Gros volume",
                "طلبات الجملة والكميات الكبيرة",
                "50 kg +",
                "Prix sur demande"
        );

        setContentView(root);
    }

    private void addProduct(
            LinearLayout root,
            String name,
            String arabic,
            String quantity,
            String price
    ) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(18), dp(15), dp(18), dp(15));
        card.setGravity(Gravity.CENTER);

        TextView n = text(name, 19);
        n.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        n.setTextColor(GREEN);
        n.setGravity(Gravity.CENTER);

        card.addView(n);

        TextView a = text(arabic, 16);
        a.setGravity(Gravity.CENTER);
        card.addView(a);

        TextView q = text(
                quantity + "   •   " + price,
                15
        );
        q.setGravity(Gravity.CENTER);
        card.addView(q);

        Button details = button(
                language.equals("fr") ? "Voir l'offre" :
                language.equals("en") ? "View offer" :
                "عرض التفاصيل"
        );

        details.setOnClickListener(v -> showOfferDetails());

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

        root.addView(card, lp);
    }

    // =====================================================
    // DETAILS
    // =====================================================

    private void showOfferDetails() {

        currentPage = "details";

        LinearLayout root = baseLayout();

        addBackButton(root);

        addHeader(root);

        TextView heading = title(
                language.equals("fr") ? "Détails de l'offre" :
                language.equals("en") ? "Offer details" :
                "تفاصيل العرض"
        );

        root.addView(heading);

        TextView product = text(
                "Déglet Nour – Bechni\n\n" +
                "دقلة النور من بشني\n\n" +
                "Qualité : Premium\n" +
                "Origine : Bechni – El Fawar – Kebili\n" +
                "Commerce direct producteur / acheteur",
                18
        );

        product.setGravity(Gravity.CENTER);
        root.addView(product);

        space(root, 20);

        Button call = button(
                language.equals("fr") ? "Appeler le vendeur" :
                language.equals("en") ? "Call seller" :
                "الاتصال بالبائع"
        );

        call.setOnClickListener(v -> callPhone());
        root.addView(call);

        Button order = button(
                language.equals("fr") ? "Demander cette offre" :
                language.equals("en") ? "Request this offer" :
                "طلب هذا العرض"
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

        setContentView(root);
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
                language.equals("fr") ? "Vendre mes dattes" :
                language.equals("en") ? "Sell my dates" :
                "بيع التمور"
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

        photo.setOnClickListener(v -> chooseImage());
        root.addView(photo);

        Button publish = button(
                language.equals("fr")
                        ? "Publier l'offre"
                        : language.equals("en")
                        ? "Publish offer"
                        : "نشر العرض"
        );

        publish.setOnClickListener(v -> {

            if (quantity.getText().toString().trim().isEmpty()) {
                quantity.setError("أدخل الكمية");
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

        setContentView(root);
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

        empty.setGravity(Gravity.CENTER);

        root.addView(empty);

        setContentView(root);
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
                "Téléphone : 51 022 448\n" +
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

        phone.setOnClickListener(v -> callPhone());
        root.addView(phone);

        Button email = button(
                language.equals("fr")
                        ? "Envoyer un email"
                        : language.equals("en")
                        ? "Send email"
                        : "إرسال بريد إلكتروني"
        );

        email.setOnClickListener(v -> sendEmail());
        root.addView(email);

        setContentView(root);
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

        languageButton.setOnClickListener(v -> changeLanguage());

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
                        "Notifications",
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

        help.setOnClickListener(v -> showContact());

        root.addView(help);

        setContentView(root);
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

    private void addHeader(LinearLayout root) {

        ImageView logo = new ImageView(this);

        logo.setImageResource(R.drawable.logo_datridha);
        logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        root.addView(
                logo,
                new LinearLayout.LayoutParams(
                        -1,
                        dp(100)
                )
        );

        View line = new View(this);
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

    private void addBackButton(LinearLayout root) {

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

        LinearLayout root = new LinearLayout(this);

        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);

        root.setPadding(
                dp(18),
                dp(20),
                dp(18),
                dp(20)
        );

        root.setBackgroundColor(CREAM);

        ScrollView scroll = new ScrollView(this);

        scroll.addView(root);

        // يتم وضع ScrollView كواجهة فعلية
        setContentView(scroll);

        return root;
    }

    // =====================================================
    // BUTTON
    // =====================================================

    private Button button(String label) {

        Button b = new Button(this);

        b.setText(label);
        b.setTextSize(17);
        b.setTextColor(WHITE);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);

        b.setBackgroundColor(GREEN);

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

    private Button smallButton(String label) {

        Button b = new Button(this);

        b.setText(label);
        b.setTextSize(14);
        b.setTextColor(GREEN);
        b.setAllCaps(false);

        b.setBackgroundColor(Color.TRANSPARENT);

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
    // TEXT
    // =====================================================

    private TextView title(String value) {

        TextView t = text(value, 25);

        t.setTextColor(BROWN);
        t.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        t.setGravity(Gravity.CENTER);

        t.setPadding(
                dp(5),
                dp(8),
                dp(5),
                dp(8)
        );

        return t;
    }

    private TextView text(
            String value,
            int size
    ) {

        TextView t = new TextView(this);

        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(BROWN);
        t.setGravity(Gravity.CENTER_VERTICAL);

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

    private EditText input(String hint) {

        EditText e = new EditText(this);

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

        Space s = new Space(this);

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

        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );

        startActivityForResult(intent, 100);
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

            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:51022448")
            );

            startActivity(intent);

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "51 022 448",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =====================================================
    // EMAIL
    // =====================================================

    private void sendEmail() {

        try {

            Intent intent = new Intent(
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

    private int dp(int value) {

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
