package com.datridha.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    // =========================
    // DATRIDHA COLORS
    // =========================
    static final int GREEN = Color.rgb(23, 107, 58);
    static final int LIGHT_GREEN = Color.rgb(46, 139, 87);
    static final int GOLD = Color.rgb(212, 175, 55);
    static final int BROWN = Color.rgb(91, 58, 30);
    static final int IVORY = Color.rgb(247, 233, 215);
    static final int CREAM = Color.rgb(249, 244, 236);
    static final int WHITE = Color.WHITE;
    static final int GRAY = Color.rgb(102, 102, 102);

    LinearLayout root;
    String language = "ar";

    ActivityResultLauncher<Intent> imagePicker;

    // =========================
    // TRANSLATIONS
    // =========================
    String tr(String ar, String fr, String en) {
        if ("fr".equals(language)) return fr;
        if ("en".equals(language)) return en;
        return ar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imagePicker = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK &&
                            result.getData() != null) {
                        Toast.makeText(
                                this,
                                tr("تم اختيار الصورة",
                                        "Image sélectionnée",
                                        "Image selected"),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        showSplash();
    }

    // =========================
    // SPLASH
    // =========================
    void showSplash() {

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(28, 45, 28, 35);

        GradientDrawable bg = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{
                        Color.rgb(12, 65, 35),
                        GREEN,
                        Color.rgb(44, 112, 67)
                }
        );
        root.setBackground(bg);

        Space top = new Space(this);
        root.addView(top, new LinearLayout.LayoutParams(
                1, 45
        ));

        ArtView art = new ArtView(this);
        art.type = 0;

        root.addView(art, new LinearLayout.LayoutParams(
                -1, 260
        ));

        TextView title = text(
                "DATRIDHA",
                34,
                Color.WHITE,
                true
        );
        title.setGravity(Gravity.CENTER);
        root.addView(title);

        TextView arabic = text(
                "دقلة النور من بشني",
                21,
                Color.rgb(255, 239, 185),
                true
        );
        arabic.setGravity(Gravity.CENTER);
        root.addView(arabic);

        TextView french = text(
                "Deglet Nour de Bechni",
                17,
                Color.WHITE,
                false
        );
        french.setGravity(Gravity.CENTER);
        root.addView(french);

        TextView slogan = text(
                "Achetez • Vendez • Échangez",
                15,
                Color.WHITE,
                false
        );
        slogan.setGravity(Gravity.CENTER);
        root.addView(slogan);

        Space space = new Space(this);
        root.addView(space, new LinearLayout.LayoutParams(
                1, 25
        ));

        Button start = button(
                tr("ابدأ الآن", "Commencer", "Start now"),
                GREEN
        );

        root.addView(start, new LinearLayout.LayoutParams(
                -1, 58
        ));

        start.setOnClickListener(v -> showHome());

        LinearLayout languages = new LinearLayout(this);
        languages.setGravity(Gravity.CENTER);
        languages.setPadding(0, 20, 0, 0);

        String[] langs = {"العربية", "Français", "English"};

        for (String l : langs) {
            TextView b = text(l, 13, Color.WHITE, false);
            b.setGravity(Gravity.CENTER);

            languages.addView(
                    b,
                    new LinearLayout.LayoutParams(
                            0, 45, 1
                    )
            );

            if (l.equals("العربية")) {
                b.setOnClickListener(v -> {
                    language = "ar";
                    showSplash();
                });
            } else if (l.equals("Français")) {
                b.setOnClickListener(v -> {
                    language = "fr";
                    showSplash();
                });
            } else {
                b.setOnClickListener(v -> {
                    language = "en";
                    showSplash();
                });
            }
        }

        root.addView(languages);

        setContentView(root);
    }

    // =========================
    // HOME
    // =========================
    void showHome() {

        baseScreen();

        addHeader(
                tr("الرئيسية", "Accueil", "Home"),
                true
        );

        ArtView banner = new ArtView(this);
        banner.type = 1;

        root.addView(
                banner,
                new LinearLayout.LayoutParams(
                        -1, 220
                )
        );

        TextView welcome = text(
                tr(
                        "دقلة النور من بشني",
                        "Deglet Nour de Bechni",
                        "Deglet Nour from Bechni"
                ),
                23,
                BROWN,
                true
        );

        welcome.setGravity(Gravity.CENTER);
        welcome.setPadding(0, 15, 0, 8);
        root.addView(welcome);

        TextView sub = text(
                tr(
                        "اشتر • بع • تعامل مباشرة",
                        "Achetez • Vendez • Échangez directement",
                        "Buy • Sell • Trade directly"
                ),
                14,
                GRAY,
                false
        );

        sub.setGravity(Gravity.CENTER);
        root.addView(sub);

        LinearLayout grid = new LinearLayout(this);
        grid.setOrientation(LinearLayout.VERTICAL);
        grid.setPadding(0, 18, 0, 5);

        addHomeRow(
                grid,
                tr("شراء", "Acheter", "Buy"),
                tr("بيع", "Vendre", "Sell"),
                2,
                3
        );

        addHomeRow(
                grid,
                tr("الطلبات", "Commandes", "Orders"),
                tr("اتصل بنا", "Contact", "Contact"),
                4,
                5
        );

        root.addView(grid);

        TextView products = text(
                tr(
                        "عروض دقلة النور",
                        "Offres Deglet Nour",
                        "Deglet Nour offers"
                ),
                20,
                GREEN,
                true
        );

        products.setPadding(5, 18, 5, 10);
        root.addView(products);

        addProductCard(
                tr("دقلة نور ممتازة", "Deglet Nour Premium",
                        "Premium Deglet Nour"),
                "5",
                "DT/kg",
                "Bechni"
        );

        addProductCard(
                tr("دقلة نور جودة أولى", "Deglet Nour Qualité 1",
                        "Deglet Nour Quality 1"),
                "10",
                "DT/kg",
                "Kebili"
        );

        addBottomNav(0);
    }

    void addHomeRow(
            LinearLayout parent,
            String one,
            String two,
            int typeOne,
            int typeTwo
    ) {

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);

        row.setPadding(0, 5, 0, 5);

        Button a = menuButton(one);
        Button b = menuButton(two);

        row.addView(
                a,
                new LinearLayout.LayoutParams(
                        0, 92, 1
                )
        );

        row.addView(
                b,
                new LinearLayout.LayoutParams(
                        0, 92, 1
                )
        );

        a.setOnClickListener(v -> {
            if (typeOne == 2) showBuy();
            if (typeOne == 3) showSell();
            if (typeOne == 4) showOrders();
            if (typeOne == 5) showContact();
        });

        b.setOnClickListener(v -> {
            if (typeTwo == 2) showBuy();
            if (typeTwo == 3) showSell();
            if (typeTwo == 4) showOrders();
            if (typeTwo == 5) showContact();
        });

        parent.addView(row);
    }

    Button menuButton(String title) {
        Button b = button(title, GREEN);
        b.setTextSize(17);
        b.setAllCaps(false);
        b.setPadding(5, 5, 5, 5);
        return b;
    }

    // =========================
    // BUY
    // =========================
    void showBuy() {

        baseScreen();

        addHeader(
                tr("شراء", "Acheter", "Buy"),
                true
        );

        EditText search = new EditText(this);
        search.setHint(
                tr(
                        "ابحث عن دقلة النور...",
                        "Rechercher Deglet Nour...",
                        "Search Deglet Nour..."
                )
        );
        search.setSingleLine(true);
        search.setPadding(20, 0, 20, 0);

        root.addView(
                search,
                new LinearLayout.LayoutParams(
                        -1, 55
                )
        );

        LinearLayout filters = new LinearLayout(this);

        String[] fs = {
                tr("الكل", "Tout", "All"),
                tr("الكمية", "Quantité", "Quantity"),
                tr("السعر", "Prix", "Price"),
                tr("المنطقة", "Région", "Region")
        };

        for (String f : fs) {
            TextView t = text(f, 12, GREEN, true);
            t.setGravity(Gravity.CENTER);
            t.setPadding(12, 8, 12, 8);

            filters.addView(
                    t,
                    new LinearLayout.LayoutParams(
                            0, 45, 1
                    )
            );
        }

        root.addView(filters);

        addOffer(
                tr("دقلة نور ممتازة",
                        "Deglet Nour Premium",
                        "Premium Deglet Nour"),
                "5 DT/kg",
                "5 tonnes",
                "Bechni",
                1
        );

        addOffer(
                tr("دقلة نور جودة أولى",
                        "Deglet Nour Qualité 1",
                        "Deglet Nour Quality 1"),
                "6 DT/kg",
                "2 tonnes",
                "El Fawar",
                2
        );

        addOffer(
                tr("دقلة نور فاخرة",
                        "Deglet Nour Luxe",
                        "Luxury Deglet Nour"),
                "7 DT/kg",
                "1 tonne",
                "Kebili",
                3
        );

        addBottomNav(1);
    }

    void addOffer(
            String name,
            String price,
            String quantity,
            String region,
            int type
    ) {

        LinearLayout card = card();

        ArtView art = new ArtView(this);
        art.type = 2;

        card.addView(
                art,
                new LinearLayout.LayoutParams(
                        -1, 125
                )
        );

        TextView n = text(name, 19, GREEN, true);
        n.setPadding(5, 10, 5, 4);
        card.addView(n);

        card.addView(text(
                price + "   •   " +
                        quantity + "   •   " +
                        region,
                14,
                BROWN,
                false
        ));

        Button details = button(
                tr("التفاصيل", "Détails", "Details"),
                GREEN
        );

        card.addView(
                details,
                new LinearLayout.LayoutParams(
                        -1, 50
                )
        );

        details.setOnClickListener(v -> showOfferDetails(name));

        root.addView(card);
    }

    // =========================
    // OFFER DETAILS
    // =========================
    void showOfferDetails(String name) {

        baseScreen();

        addHeader(
                tr("تفاصيل العرض", "Détails de l'offre",
                        "Offer details"),
                false
        );

        ArtView art = new ArtView(this);
        art.type = 2;

        root.addView(
                art,
                new LinearLayout.LayoutParams(
                        -1, 250
                )
        );

        TextView title = text(name, 25, GREEN, true);
        title.setPadding(0, 15, 0, 10);
        root.addView(title);

        addInfo(
                tr("السعر", "Prix", "Price"),
                "5 - 7 DT/kg"
        );

        addInfo(
                tr("الكمية", "Quantité", "Quantity"),
                "1 - 5 tonnes"
        );

        addInfo(
                tr("الأصل", "Origine", "Origin"),
                "Bechni - El Fawar - Kebili"
        );

        addInfo(
                tr("الجودة", "Qualité", "Quality"),
                tr("ممتازة", "Premium", "Premium")
        );

        addInfo(
                tr("التغليف", "Emballage", "Packaging"),
                tr("حسب الطلب", "Selon la demande",
                        "On request")
        );

        Button contact = button(
                tr("الاتصال بصاحب العرض",
                        "Contacter le vendeur",
                        "Contact seller"),
                GREEN
        );

        root.addView(
                contact,
                new LinearLayout.LayoutParams(
                        -1, 56
                )
        );

        contact.setOnClickListener(v -> callPhone());

        Button favorite = button(
                tr("♡ إضافة إلى المفضلة",
                        "♡ Ajouter aux favoris",
                        "♡ Add to favorites"),
                BROWN
        );

        root.addView(
                favorite,
                new LinearLayout.LayoutParams(
                        -1, 56
                )
        );
    }

    void addInfo(String label, String value) {

        TextView t = text(
                label + " : " + value,
                16,
                GRAY,
                false
        );

        t.setPadding(5, 7, 5, 7);
        root.addView(t);
    }

    // =========================
    // SELL
    // =========================
    void showSell() {

        baseScreen();

        addHeader(
                tr("نشر عرض بيع", "Publier une offre",
                        "Publish sell offer"),
                true
        );

        field(
                tr("اسم البائع", "Nom du vendeur", "Seller name")
        );

        field(
                tr("الكمية", "Quantité", "Quantity")
        );

        field(
                tr("السعر", "Prix", "Price")
        );

        field(
                tr("رقم الهاتف", "Téléphone", "Phone")
        );

        field(
                tr("المنطقة", "Région", "Region")
        );

        EditText description = field(
                tr("وصف المنتج", "Description du produit",
                        "Product description")
        );

        description.setMinHeight(100);

        Button photo = button(
                tr("📷 إضافة صورة",
                        "📷 Ajouter une photo",
                        "📷 Add photo"),
                BROWN
        );

        root.addView(
                photo,
                new LinearLayout.LayoutParams(
                        -1, 55
                )
        );

        photo.setOnClickListener(v -> chooseImage());

        Button publish = button(
                tr("نشر العرض",
                        "Publier l'offre",
                        "Publish offer"),
                GREEN
        );

        root.addView(
                publish,
                new LinearLayout.LayoutParams(
                        -1, 58
                )
        );

        publish.setOnClickListener(v -> Toast.makeText(
                this,
                tr(
                        "تم نشر العرض بنجاح",
                        "Offre publiée avec succès",
                        "Offer published successfully"
                ),
                Toast.LENGTH_LONG
        ).show());

        addBottomNav(2);
    }

    EditText field(String hint) {

        EditText e = new EditText(this);
        e.setHint(hint);
        e.setSingleLine(false);
        e.setPadding(18, 0, 18, 0);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.WHITE);
        bg.setCornerRadius(18);
        bg.setStroke(1, Color.rgb(225, 216, 203));
        e.setBackground(bg);

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(-1, 55);

        lp.setMargins(0, 7, 0, 7);

        root.addView(e, lp);

        return e;
    }

    // =========================
    // ORDERS
    // =========================
    void showOrders() {

        baseScreen();

        addHeader(
                tr("الطلبات", "Commandes", "Orders"),
                true
        );

        LinearLayout tabs = new LinearLayout(this);

        String[] ts = {
                tr("الكل", "Toutes", "All"),
                tr("قيد التنفيذ", "En cours", "In progress"),
                tr("مستلمة", "Reçues", "Received")
        };

        for (String s : ts) {
            TextView t = text(s, 13, GREEN, true);
            t.setGravity(Gravity.CENTER);

            tabs.addView(
                    t,
                    new LinearLayout.LayoutParams(
                            0, 50, 1
                    )
            );
        }

        root.addView(tabs);

      addOrder(
        tr("طلب دقلة نور",
                "Commande Deglet Nour",
                "Deglet Nour order"),
        "2 tonnes",
        "05/10/2026",
        tr("قيد التنفيذ",
                "En cours",
                "In progress")
);  
