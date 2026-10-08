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

    // الخلفية
    ImageView background = new ImageView(this);

    background.setImageResource(
            R.drawable.splash_oasis
    );

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

    // طبقة شفافة لتحسين وضوح المحتوى
    View shade = new View(this);

    GradientDrawable shadeBg =
            new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{
                            0x18000000,
                            0x35000000,
                            0xB0000000
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

    // المحتوى
    LinearLayout content =
            new LinearLayout(this);

    content.setOrientation(
            LinearLayout.VERTICAL
    );

    content.setGravity(
            Gravity.CENTER_HORIZONTAL
    );

    content.setPadding(
            24,
            42,
            24,
            24
    );

    FrameLayout.LayoutParams contentLp =
            new FrameLayout.LayoutParams(
                    -1,
                    -1
            );

    contentLp.gravity = Gravity.CENTER;

    root.addView(
            content,
            contentLp
    );

    // الشعار
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
                    132,
                    132
            );

    logoLp.gravity =
            Gravity.CENTER_HORIZONTAL;

    content.addView(
            logo,
            logoLp
    );

    // اسم التطبيق
    TextView brand =
            text(
                    "DATRIDHA",
                    32,
                    GOLD
            );

    brand.setTypeface(
            Typeface.create(
                    Typeface.SERIF,
                    Typeface.BOLD
            )
    );

    brand.setLetterSpacing(
            .15f
    );

    brand.setShadowLayer(
            7,
            0,
            3,
            Color.BLACK
    );

    content.addView(
            brand,
            new LinearLayout.LayoutParams(
                    -1,
                    58
            )
    );

    // الاسم بالعربية
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
                    52
            )
    );

    // الفرنسية
    TextView french =
            text(
                    "Deglet Nour de Bechni",
                    17,
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
                    42
            )
    );

    addSpace(
            content,
            10
    );

    // شارات الثقة
    LinearLayout badges =
            new LinearLayout(this);

    badges.setGravity(
            Gravity.CENTER
    );

    badges.setPadding(
            0,
            0,
            0,
            8
    );

    String[] badgeTexts = {
            "موثوق",
            "جودة",
            "تجارة مباشرة"
    };

    for (String badgeText : badgeTexts) {

        TextView badge =
                text(
                        badgeText,
                        13,
                        GREEN
                );

        badge.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        badge.setBackground(
                bg(
                        0xFFF7E9D7,
                        28
                )
        );

        badge.setPadding(
                12,
                5,
                12,
                5
        );

        LinearLayout.LayoutParams bp =
                new LinearLayout.LayoutParams(
                        -2,
                        38
                );

        bp.setMargins(
                4,
                0,
                4,
                0
        );

        badges.addView(
                badge,
                bp
        );
    }

    content.addView(
            badges
    );

    // الشعار
    TextView slogan =
            text(
                    "Achetez • Vendez • Échangez",
                    16,
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
                    40
            )
    );

    addSpace(
            content,
            12
    );

    // زر ابدأ الآن
    Button startButton =
            button(
                    "ابدأ الآن"
            );

    startButton.setTextSize(
            19
    );

    startButton.setTypeface(
            Typeface.DEFAULT,
            Typeface.BOLD
    );

    startButton.setBackground(
            bg(
                    GREEN,
                    55
            )
    );

    startButton.setOnClickListener(
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
            startButton,
            startLp
    );

    // اللغات
    LinearLayout languages =
            new LinearLayout(this);

    languages.setGravity(
            Gravity.CENTER
    );

    languages.setPadding(
            0,
            10,
            0,
            0
    );

    Button ar =
            button(
                    "العربية"
            );

    Button fr =
            button(
                    "Français"
            );

    Button en =
            button(
                    "English"
            );

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
            new LinearLayout.LayoutParams(
                    100,
                    46
            )
    );

    languages.addView(
            fr,
            new LinearLayout.LayoutParams(
                    100,
                    46
            )
    );

    languages.addView(
            en,
            new LinearLayout.LayoutParams(
                    100,
                    46
            )
    );

    content.addView(
            languages
    );

    setContentView(
            root
    );
}
