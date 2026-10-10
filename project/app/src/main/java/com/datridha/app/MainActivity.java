package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.content.ClipData;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.HorizontalScrollView;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {

    private final int GREEN = Color.rgb(16, 78, 54);
    private final int DARK_GREEN = Color.rgb(9, 54, 38);
    private final int GOLD = Color.rgb(210, 166, 65);
    private final int CREAM = Color.rgb(249, 246, 236);
    private final int WHITE = Color.WHITE;
    private final int TEXT = Color.rgb(45, 50, 44);

    private LinearLayout root;
    private LinearLayout content;
    private String language = "ar";
    private String currentPage = "home";

    private EditText titleInput;
    private EditText sellerInput;
    private EditText phoneInput;
    private EditText varietyInput;
    private EditText quantityInput;
    private EditText priceInput;
    private EditText descriptionInput;

    private final ArrayList<Uri> selectedPhotos = new ArrayList<>();
    private final ArrayList<Listing> listings = new ArrayList<>();

    private static final int PICK_IMAGES = 2001;
    private static final int MAX_IMAGES = 10;

    private SharedPreferences preferences;

    private static class Listing {
        String title;
        String seller;
        String phone;
        String variety;
        String quantity;
        String price;
        String description;
        ArrayList<String> photos = new ArrayList<>();

        JSONObject toJson() throws Exception {
            JSONObject obj = new JSONObject();
            obj.put("title", title);
            obj.put("seller", seller);
            obj.put("phone", phone);
            obj.put("variety", variety);
            obj.put("quantity", quantity);
            obj.put("price", price);
            obj.put("description", description);

            JSONArray arr = new JSONArray();
            for (String photo : photos) {
                arr.put(photo);
            }
            obj.put("photos", arr);
            return obj;
        }

        static Listing fromJson(JSONObject obj) throws Exception {
            Listing item = new Listing();
            item.title = obj.optString("title");
            item.seller = obj.optString("seller");
            item.phone = obj.optString("phone");
            item.variety = obj.optString("variety");
            item.quantity = obj.optString("quantity");
            item.price = obj.optString("price");
            item.description = obj.optString("description");

            JSONArray arr = obj.optJSONArray("photos");
            if (arr != null) {
                for (int i = 0; i < arr.length(); i++) {
                    item.photos.add(arr.getString(i));
                }
            }
            return item;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("DATRIDHA_SETTINGS", MODE_PRIVATE);
        language = preferences.getString("language", "ar");
        loadListings();

        showSplash();
    }

    private String tr(String ar, String fr, String en) {
        if ("fr".equals(language)) return fr;
        if ("en".equals(language)) return en;
        return ar;
    }

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private LinearLayout vertical() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        return layout;
    }

    private LinearLayout horizontal() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        return layout;
    }

    private LinearLayout.LayoutParams lp(int width, int height) {
        return new LinearLayout.LayoutParams(width, height);
    }

    private LinearLayout.LayoutParams margin(int width, int height, int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(dp(left), dp(top), dp(right), dp(bottom));
        return params;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setGravity(Gravity.CENTER_VERTICAL);
        if (bold) view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        view.setPadding(dp(2), dp(3), dp(2), dp(3));
        return view;
    }

    private Button button(String label, int background, int foreground) {
        Button btn = new Button(this);
        btn.setText(label);
        btn.setTextColor(foreground);
        btn.setTextSize(14);
        btn.setAllCaps(false);
        btn.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(background));
        return btn;
    }

    private EditText edit(String hint, int inputType) {
        EditText field = new EditText(this);
        field.setHint(hint);
        field.setTextSize(15);
        field.setTextColor(TEXT);
        field.setHintTextColor(Color.GRAY);
        field.setSingleLine(false);
        field.setInputType(inputType);
        field.setPadding(dp(12), dp(10), dp(12), dp(10));
        field.setBackgroundTintList(android.content.res.ColorStateList.valueOf(GOLD));
        return field;
    }

    private ScrollView newScroll() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        return scroll;
    }

    private void buildShell(String title) {
        root = vertical();
        root.setBackgroundColor(CREAM);

        LinearLayout header = horizontal();
        header.setBackgroundColor(DARK_GREEN);
        header.setPadding(dp(12), dp(10), dp(12), dp(10));

        TextView brand = text("DATRIDHA", 22, GOLD, true);
        header.addView(brand, new LinearLayout.LayoutParams(0, dp(48), 1));

        TextView pageTitle = text(title, 15, WHITE, true);
        pageTitle.setGravity(Gravity.CENTER);
        header.addView(pageTitle, lp(-2, dp(48)));

        root.addView(header, lp(-1, -2));

        ScrollView scroll = newScroll();
        content = vertical();
        content.setPadding(dp(14), dp(12), dp(14), dp(18));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        LinearLayout nav = horizontal();
        nav.setBackgroundColor(DARK_GREEN);
        nav.setPadding(dp(3), dp(5), dp(3), dp(5));

        addNavButton(nav, tr("الرئيسية", "Accueil", "Home"), "home");
        addNavButton(nav, tr("شراء", "Acheter", "Buy"), "buy");
        addNavButton(nav, tr("بيع", "Vendre", "Sell"), "sell");
        addNavButton(nav, tr("طلباتي", "Mes annonces", "My listings"), "orders");
        addNavButton(nav, tr("المزيد", "Menu", "More"), "more");

        root.addView(nav, lp(-1, -2));
        setContentView(root);
    }

    private void addNavButton(LinearLayout nav, String label, final String page) {
    LinearLayout item = new LinearLayout(this);
    item.setOrientation(LinearLayout.VERTICAL);
    item.setGravity(Gravity.CENTER);
    item.setPadding(dp(1), dp(3), dp(1), dp(3));
    item.setBackgroundColor(DARK_GREEN);

    TextView icon = new TextView(this);
    icon.setGravity(Gravity.CENTER);
    icon.setTextSize(21);
    icon.setTextColor(GOLD);

    switch (page) {
        case "home":
            icon.setText("⌂");
            break;
        case "buy":
            icon.setText("🛒");
            break;
        case "sell":
            icon.setText("🏷️");
            break;
        case "orders":
            icon.setText("📦");
            break;
        case "more":
            icon.setText("☰");
            break;
        default:
            icon.setText("●");
            break;
    }

    item.addView(icon, new LinearLayout.LayoutParams(-1, dp(25)));

    TextView textLabel = new TextView(this);
    textLabel.setText(label);
    textLabel.setTextSize(10);
    textLabel.setTextColor(WHITE);
    textLabel.setGravity(Gravity.CENTER);
    textLabel.setSingleLine(true);

    item.addView(textLabel, new LinearLayout.LayoutParams(-1, dp(18)));

    item.setOnClickListener(v -> openPage(page));

    nav.addView(item, new LinearLayout.LayoutParams(0, dp(48), 1));
}

    private void showSplash() {
        root = vertical();
        root.setBackgroundColor(DARK_GREEN);
        root.setGravity(Gravity.CENTER);

        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.image_2bd7123b);
        logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
logo.setAdjustViewBounds(true);
logo.setPadding(dp(4), 0, dp(4), 0);
root.addView(logo, lp(-1, dp(420)));
        TextView name = text("DATRIDHA", 34, GOLD, true);
        name.setGravity(Gravity.CENTER);
        root.addView(name, margin(-1, dp(55), 12, 12, 12, 0));

        TextView subtitle = text(
                tr("سوق دقلة النور بالجملة", "Le marché de Deglet Nour en gros", "Deglet Nour Wholesale Market"),
                17, WHITE, true);
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle, margin(-1, dp(50), 12, 4, 12, 10));

        Button start = button(tr("الدخول إلى التطبيق", "Entrer dans l'application", "Enter the app"), GOLD, DARK_GREEN);
        root.addView(start, margin(-1, dp(55), 28, 20, 28, 0));
        start.setOnClickListener(v -> openPage("home"));

        setContentView(root);
    }

    private void openPage(String page) {
        currentPage = page;

        switch (page) {
            case "home":
                showHome();
                break;
            case "buy":
                showBuy();
                break;
            case "sell":
                showSell();
                break;
            case "orders":
                showOrders();
                break;
            case "more":
                showMore();
                break;
            case "settings":
                showSettings();
                break;
            case "contact":
                showContact();
                break;
            default:
                showHome();
        }
    }

    private void addHeading(String title) {
        TextView heading = text(title, 23, GREEN, true);
        heading.setGravity(Gravity.CENTER);
        content.addView(heading, margin(-1, dp(54), 0, 2, 0, 8));
    }

    private void addParagraph(String value) {
        TextView paragraph = text(value, 15, TEXT, false);
        paragraph.setGravity(Gravity.CENTER);
        paragraph.setPadding(dp(8), dp(8), dp(8), dp(8));
        content.addView(paragraph, margin(-1, -2, 0, 4, 0, 8));
    }

    private void addSection(String value) {
        TextView section = text(value, 18, GREEN, true);
        content.addView(section, margin(-1, dp(42), 0, 8, 0, 2));
    }

    private void addPhoto(int resource, String description, int height) {
        ImageView image = new ImageView(this);
        image.setImageResource(resource);
        
        image.setContentDescription(description);
        content.addView(image, margin(-1, dp(height), 0, 6, 0, 6));
    }

    private void showHome() {
        buildShell(tr("الرئيسية", "Accueil", "Home"));
        addHeading(tr("دقلة النور من بشني", "Deglet Nour de Bechni", "Deglet Nour from Bechni"));
        addParagraph(tr(
                "من المنتج إلى التاجر، جودة تونسية وأصالة من واحات الجنوب.",
                "Du producteur au grossiste, qualité tunisienne et authenticité des oasis du Sud.",
                "From producer to wholesaler, Tunisian quality from the southern oases."));

        addPhoto(R.drawable.image_69792f84, "Deglet Nour packaging", 280);
        addSection(tr("سوق التمور بالجملة", "Marché de dattes en gros", "Wholesale Dates Market"));
        addParagraph(tr(
                "اكتشف عروض التمور، أضف إعلانك، وتواصل مباشرة مع البائع.",
                "Découvrez les offres, publiez une annonce et contactez directement le vendeur.",
                "Discover date offers, publish a listing, and contact the seller."));

        Button buy = button(tr("اكتشف التمور", "Découvrir les dattes", "Explore dates"), GREEN, WHITE);
        content.addView(buy, margin(-1, dp(52), 0, 6, 0, 4));
        buy.setOnClickListener(v -> openPage("buy"));

        Button sell = button(tr("أضف إعلان بيع", "Publier une annonce", "Post a listing"), GOLD, DARK_GREEN);
        content.addView(sell, margin(-1, dp(52), 0, 4, 0, 10));
        sell.setOnClickListener(v -> openPage("sell"));

        addPhoto(R.drawable.image_562aeaad, "Oasis and dates", 180);
        addPhoto(R.drawable.dates_basket_1, "Dates in a traditional basket", 180);
        addPhoto(R.drawable.dates_basket_2, "Dates in a traditional basket", 180);
    }

    private void showBuy() {
        buildShell(tr("شراء التمور", "Acheter des dattes", "Buy dates"));
        addHeading(tr("عروض دقلة النور", "Offres Deglet Nour", "Deglet Nour Offers"));
        addParagraph(tr(
                "تصفح العروض المنشورة على هذا الهاتف. الأسعار والكميات يحددها البائع.",
                "Parcourez les annonces enregistrées sur ce téléphone. Le vendeur fixe les prix et quantités.",
                "Browse listings saved on this phone. Sellers set their own prices and quantities."));

        addPhoto(R.drawable.image_69792f84, "Deglet Nour", 180);
        addPhoto(R.drawable.image_562aeaad, "Date oasis", 150);

        if (listings.isEmpty()) {
            addParagraph(tr(
                    "لا توجد إعلانات محفوظة بعد. يمكنك إضافة إعلان من صفحة البيع.",
                    "Aucune annonce enregistrée. Vous pouvez en ajouter depuis la page Vente.",
                    "No saved listings yet. You can add one from the Sell page."));
        } else {
            for (int i = listings.size() - 1; i >= 0; i--) {
                addListingCard(listings.get(i));
            }
        }
    }

    private void addListingCard(Listing item) {
        LinearLayout card = vertical();
        card.setBackgroundColor(WHITE);
        card.setPadding(dp(12), dp(10), dp(12), dp(10));

        TextView title = text(item.title, 18, GREEN, true);
        card.addView(title, lp(-1, -2));

        TextView variety = text(
                tr("الصنف: ", "Variété : ", "Variety: ") + item.variety,
                14, TEXT, false);
        card.addView(variety, lp(-1, -2));

        TextView quantity = text(
                tr("الكمية: ", "Quantité : ", "Quantity: ") + item.quantity,
                14, TEXT, false);
        card.addView(quantity, lp(-1, -2));

        TextView price = text(
                tr("السعر: ", "Prix : ", "Price: ") + item.price + " DT",
                16, GREEN, true);
        card.addView(price, lp(-1, -2));

        if (item.photos.size() > 0) {
            ImageView image = new ImageView(this);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            try {
                image.setImageURI(Uri.parse(item.photos.get(0)));
            } catch (Exception ignored) {
            }
            card.addView(image, margin(-1, dp(150), 0, 8, 0, 8));
        }

        TextView seller = text(
                tr("البائع: ", "Vendeur : ", "Seller: ") + item.seller,
                14, TEXT, false);
        card.addView(seller, lp(-1, -2));

        if (!item.description.isEmpty()) {
            TextView desc = text(item.description, 14, TEXT, false);
            card.addView(desc, lp(-1, -2));
        }

        Button call = button(
                tr("اتصال بالبائع", "Appeler le vendeur", "Call seller"),
                GOLD, DARK_GREEN);
        card.addView(call, margin(-1, dp(48), 0, 8, 0, 2));
        call.setOnClickListener(v -> {
            if (item.phone != null && !item.phone.trim().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.phone));
                startActivity(intent);
            } else {
                Toast.makeText(this, tr("لا يوجد رقم هاتف", "Aucun numéro", "No phone number"), Toast.LENGTH_SHORT).show();
            }
        });

        content.addView(card, margin(-1, -2, 0, 6, 0, 12));
    }

    private void showSell() {
        selectedPhotos.clear();
        buildShell(tr("إضافة إعلان", "Ajouter une annonce", "Create Listing"));
        addHeading(tr("أضف إعلانك", "Publiez votre annonce", "Post Your Listing"));
        addParagraph(tr(
                "أدخل معلومات التمور وأرفق حتى 10 صور.",
                "Saisissez les informations et ajoutez jusqu'à 10 photos.",
                "Enter the date details and add up to 10 photos."));

        titleInput = edit(tr("عنوان الإعلان", "Titre de l'annonce", "Listing title"),
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        content.addView(titleInput, margin(-1, dp(54), 0, 5, 0, 5));

        sellerInput = edit(tr("اسم البائع", "Nom du vendeur", "Seller name"),
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        content.addView(sellerInput, margin(-1, dp(54), 0, 5, 0, 5));

        phoneInput = edit(tr("رقم الهاتف", "Numéro de téléphone", "Phone number"),
                InputType.TYPE_CLASS_PHONE);
        content.addView(phoneInput, margin(-1, dp(54), 0, 5, 0, 5));

        varietyInput = edit(tr("الصنف أو الدرجة: دقلة نور ممتازة، عادية...", "Variété ou catégorie", "Variety or grade"),
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        content.addView(varietyInput, margin(-1, dp(54), 0, 5, 0, 5));

        quantityInput = edit(tr("الكمية بالكلغ أو بالطن", "Quantité en kg ou tonnes", "Quantity in kg or tonnes"),
                InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        content.addView(quantityInput, margin(-1, dp(54), 0, 5, 0, 5));

        priceInput = edit(tr("السعر بالدينار التونسي", "Prix en dinars tunisiens", "Price in Tunisian dinars"),
                InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        content.addView(priceInput, margin(-1, dp(54), 0, 5, 0, 5));

        descriptionInput = edit(tr("وصف التمور والجودة ومكان التسليم", "Description, qualité et lieu de livraison", "Description, quality and pickup location"),
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        descriptionInput.setMinLines(3);
        content.addView(descriptionInput, margin(-1, dp(105), 0, 5, 0, 5));

        Button choosePhotos = button(
                tr("اختيار الصور (حتى 10)", "Choisir des photos (max. 10)", "Choose photos (up to 10)"),
                GREEN, WHITE);
        content.addView(choosePhotos, margin(-1, dp(52), 0, 8, 0, 4));
        choosePhotos.setOnClickListener(v -> pickPhotos());

        HorizontalScrollView photoScroll = new HorizontalScrollView(this);
        LinearLayout photoStrip = horizontal();
        photoStrip.setTag("photoStrip");
        photoScroll.addView(photoStrip);
        content.addView(photoScroll, margin(-1, dp(115), 0, 2, 0, 8));

        Button publish = button(
                tr("حفظ الإعلان", "Enregistrer l'annonce", "Save Listing"),
                GOLD, DARK_GREEN);
        content.addView(publish, margin(-1, dp(55), 0, 8, 0, 12));
        publish.setOnClickListener(v -> saveListing());
    }

    private void pickPhotos() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        try {
            startActivityForResult(intent, PICK_IMAGES);
        } catch (Exception e) {
            Toast.makeText(this, tr("تعذر فتح الصور", "Impossible d'ouvrir les photos", "Unable to open photos"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != PICK_IMAGES || resultCode != RESULT_OK || data == null) return;

        if (data.getClipData() != null) {
            ClipData clip = data.getClipData();
            for (int i = 0; i < clip.getItemCount(); i++) {
                if (selectedPhotos.size() >= MAX_IMAGES) break;
                Uri uri = clip.getItemAt(i).getUri();
                if (!selectedPhotos.contains(uri)) selectedPhotos.add(uri);
            }
        } else if (data.getData() != null) {
            selectedPhotos.clear();
            selectedPhotos.add(data.getData());
        }

        refreshPhotoStrip();
        Toast.makeText(this,
                tr("عدد الصور: ", "Nombre de photos : ", "Number of photos: ") + selectedPhotos.size(),
                Toast.LENGTH_SHORT).show();
    }

    private void refreshPhotoStrip() {
        if (content == null) return;

        HorizontalScrollView scroll = null;
        for (int i = 0; i < content.getChildCount(); i++) {
            View view = content.getChildAt(i);
            if (view instanceof HorizontalScrollView) {
                scroll = (HorizontalScrollView) view;
            }
        }

        if (scroll == null) return;

        LinearLayout strip = (LinearLayout) scroll.getChildAt(0);
        strip.removeAllViews();

        for (int i = 0; i < selectedPhotos.size(); i++) {
            final int index = i;
            LinearLayout item = vertical();

            ImageView preview = new ImageView(this);
            preview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            preview.setImageURI(selectedPhotos.get(i));
            item.addView(preview, lp(dp(90), dp(75)));

            Button remove = button("×", Color.RED, WHITE);
            item.addView(remove, lp(dp(90), dp(36)));
            remove.setOnClickListener(v -> {
                if (index < selectedPhotos.size()) {
                    selectedPhotos.remove(index);
                    refreshPhotoStrip();
                }
            });

            strip.addView(item, margin(dp(90), dp(115), 3, 0, 3, 0));
        }
    }

    private void saveListing() {
        String title = value(titleInput);
        String seller = value(sellerInput);
        String phone = value(phoneInput);
        String variety = value(varietyInput);
        String quantity = value(quantityInput);
        String price = value(priceInput);
        String description = value(descriptionInput);

        if (title.isEmpty() || seller.isEmpty() || phone.isEmpty()
                || variety.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            Toast.makeText(this,
                    tr("يرجى ملء الحقول الأساسية", "Veuillez remplir les champs obligatoires", "Please complete the required fields"),
                    Toast.LENGTH_LONG).show();
            return;
        }

        Listing item = new Listing();
        item.title = title;
        item.seller = seller;
        item.phone = phone;
        item.variety = variety;
        item.quantity = quantity;
        item.price = price;
        item.description = description;

        for (Uri uri : selectedPhotos) {
            item.photos.add(uri.toString());
        }

        listings.add(item);
        persistListings();

        hideKeyboard();

        Toast.makeText(this,
                tr("تم حفظ الإعلان على هذا الهاتف", "Annonce enregistrée sur ce téléphone", "Listing saved on this phone"),
                Toast.LENGTH_LONG).show();

        openPage("orders");
    }

    private String value(EditText input) {
        return input == null || input.getText() == null ? "" : input.getText().toString().trim();
    }

    private void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null && getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception ignored) {
        }
    }

    private void showOrders() {
        buildShell(tr("إعلاناتي", "Mes annonces", "My Listings"));
        addHeading(tr("إعلاناتي المحفوظة", "Mes annonces enregistrées", "My Saved Listings"));

        if (listings.isEmpty()) {
            addParagraph(tr(
                    "لم تضف أي إعلان بعد. اضغط على «بيع» لإضافة إعلان جديد.",
                    "Aucune annonce pour le moment. Appuyez sur « Vendre » pour en ajouter une.",
                    "You haven't posted a listing yet. Tap Sell to add one."));
        } else {
            for (int i = listings.size() - 1; i >= 0; i--) {
                addListingCard(listings.get(i));

                final int index = i;
                Button delete = button(
                        tr("حذف الإعلان", "Supprimer l'annonce", "Delete listing"),
                        Color.RED, WHITE);
                content.addView(delete, margin(-1, dp(45), 0, 0, 0, 12));
                delete.setOnClickListener(v -> {
                    listings.remove(index);
                    persistListings();
                    showOrders();
                });
            }
        }

        Button add = button(tr("إضافة إعلان جديد", "Ajouter une annonce", "Add a new listing"), GOLD, DARK_GREEN);
        content.addView(add, margin(-1, dp(50), 0, 8, 0, 10));
        add.setOnClickListener(v -> openPage("sell"));
    }

    private void showMore() {
        buildShell(tr("المزيد", "Menu", "More"));
        addHeading(tr("القائمة", "Menu principal", "Menu"));

        Button settings = button(tr("اللغة والإعدادات", "Langue et paramètres", "Language and Settings"), GREEN, WHITE);
        content.addView(settings, margin(-1, dp(55), 0, 6, 0, 8));
        settings.setOnClickListener(v -> openPage("settings"));

        Button contact = button(tr("اتصل بنا", "Contactez-nous", "Contact us"), GOLD, DARK_GREEN);
        content.addView(contact, margin(-1, dp(55), 0, 6, 0, 8));
        contact.setOnClickListener(v -> openPage("contact"));

        addParagraph(tr(
                "DATRIDHA — سوق دقلة النور بالجملة",
                "DATRIDHA — marché de Deglet Nour en gros",
                "DATRIDHA — Deglet Nour Wholesale Market"));
    }

    private void showSettings() {
        buildShell(tr("الإعدادات", "Paramètres", "Settings"));
        addHeading(tr("اختيار اللغة", "Choisir la langue", "Choose Language"));

        RadioGroup group = new RadioGroup(this);
        group.setOrientation(RadioGroup.VERTICAL);

        RadioButton arabic = new RadioButton(this);
        arabic.setText("العربية");
        arabic.setTextSize(18);
        arabic.setTextColor(TEXT);
        group.addView(arabic);

        RadioButton french = new RadioButton(this);
        french.setText("Français");
        french.setTextSize(18);
        french.setTextColor(TEXT);
        group.addView(french);

        RadioButton english = new RadioButton(this);
        english.setText("English");
        english.setTextSize(18);
        english.setTextColor(TEXT);
        group.addView(english);

        if ("fr".equals(language)) french.setChecked(true);
        else if ("en".equals(language)) english.setChecked(true);
        else arabic.setChecked(true);

        content.addView(group, margin(-1, -2, 0, 5, 0, 10));

        Button save = button(tr("حفظ اللغة", "Enregistrer la langue", "Save Language"), GOLD, DARK_GREEN);
        content.addView(save, margin(-1, dp(52), 0, 8, 0, 10));

        save.setOnClickListener(v -> {
            if (french.isChecked()) language = "fr";
            else if (english.isChecked()) language = "en";
            else language = "ar";

            preferences.edit().putString("language", language).apply();
            Toast.makeText(this,
                    tr("تم حفظ اللغة", "Langue enregistrée", "Language saved"),
                    Toast.LENGTH_SHORT).show();
            openPage("home");
        });
    }

    private void showContact() {
        buildShell(tr("اتصل بنا", "Contact", "Contact"));
        addHeading(tr("تواصل معنا", "Contactez-nous", "Get in Touch"));

        addParagraph(tr(
                "للاستفسار حول عروض التمور أو التطبيق، يمكنك الاتصال بنا.",
                "Pour toute question sur les offres de dattes ou l'application, contactez-nous.",
                "For questions about date offers or the app, contact us."));

        addSection(tr("الهاتف", "Téléphone", "Phone"));
        TextView phone = text("+216 51 022 448", 18, GREEN, true);
        content.addView(phone, margin(-1, dp(45), 0, 0, 0, 6));

        Button call = button(tr("اتصال", "Appeler", "Call"), GREEN, WHITE);
        content.addView(call, margin(-1, dp(50), 0, 0, 0, 10));
        call.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+21651022448"));
            startActivity(intent);
        });

        addSection(tr("البريد الإلكتروني", "E-mail", "Email"));
        TextView email = text("Ridhatouil1992@gmail.com", 16, GREEN, true);
        content.addView(email, margin(-1, dp(45), 0, 0, 0, 6));

        Button mail = button(tr("إرسال بريد", "Envoyer un e-mail", "Send email"), GOLD, DARK_GREEN);
        content.addView(mail, margin(-1, dp(50), 0, 0, 0, 10));
        mail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:Ridhatouil1992@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "DATRIDHA");
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, tr("لا يوجد تطبيق بريد", "Aucune application e-mail", "No email app found"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void persistListings() {
        try {
            JSONArray arr = new JSONArray();
            for (Listing item : listings) {
                arr.put(item.toJson());
            }
            preferences.edit().putString("listings", arr.toString()).apply();
        } catch (Exception ignored) {
        }
    }

    private void loadListings() {
        listings.clear();
        String json = preferences.getString("listings", "[]");

        try {
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                listings.add(Listing.fromJson(arr.getJSONObject(i)));
            }
        } catch (Exception ignored) {
        }
    }
}
