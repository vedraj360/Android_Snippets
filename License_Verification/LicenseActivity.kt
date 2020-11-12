private const val TAG = "LicenseActivity"

class LicenseActivity : AppCompatActivity() {

    // * Class from Licensing Verification Library
    private var mChecker: LicenseChecker? = null
    private var mLicenseCheckerCallback: LicenseCheckerCallback? = null

    // * if license is valid its sets to true.
    var licensed = false
    val SALT = byteArrayOf(
        15,
        61,
        -21,
        41,
        -86,
        32,
        -11,
        13,
        -57,
        -14,
        -24,
        -58,
        44,
        -24,
        12,
        -37,
        83,
        -73,
        -99,
        72
    )

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // * Initialize License Callback which listen for verification response from google play
        mLicenseCheckerCallback = MyLicenseCheckerCallback()
        // * Required to pass it to create unique identifier
        val deviceId = Secure.getString(contentResolver, Secure.ANDROID_ID)

        /*
           * Initialize LicenseChecker
           * Requires 3 parameters
           * Current Context
           * ServerManagedPolicy (This helps to check license even when device is offline by using caching)
           * BASE64 Key --> Which you have got from developer console of particular app.
         */

        /*
        ? AESObfuscator ( An Obfuscator that uses AES to encrypt data )
        ? @param salt an array of random bytes to use for each (un)obfuscation
        ? @param applicationId application identifier, e.g. the package name
        ? @param deviceId device identifier. Use as many sources as possible to
         */

        mChecker = LicenseChecker(
            this,
            ServerManagedPolicy(this, AESObfuscator(SALT, packageName, deviceId)),
            BASE64_PUBLIC_KEY
        )


        if (isInternetAvailable(this)) {
            doCheck()
        }

    }


    private fun doCheck() {
        mChecker!!.checkAccess(mLicenseCheckerCallback)
    }

    private inner class MyLicenseCheckerCallback : LicenseCheckerCallback {
        override fun applicationError(errorCode: Int) {
            Log.i("License", "Error: $errorCode")
            if (isFinishing) {
                // Don't update UI if Activity is finishing.
                return
            }
            licensed = true
            createDialog()

        }

        override fun dontAllow(reason: Int) {
            if (isFinishing) {
                // Don't update UI if Activity is finishing.
                return
            }
            Log.i("License", "Denied!");
            Log.i("License", "Reason for denial: $reason")

            //You can do other things here, like saving the licensed status to a
            //SharedPreference so the app only has to check the license once.

            licensed = false
            createDialog()

        }

        override fun allow(reason: Int) {
            if (isFinishing) {
                // Don't update UI if Activity is finishing.
                return
            }

            Log.i("License", "Accepted!");
            licensed = true
        }
    }

    fun createDialog() {
// We have only one dialog.
        val alertDialog: AlertDialog.Builder?
        alertDialog = AlertDialog.Builder(this)
            .setTitle("UNLICENSED APPLICATION")
            .setMessage("This application is not licensed, please buy it from the play store.")
            .setPositiveButton(
                "Buy"
            ) { dialog, _ ->
                val marketIntent = Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "http://market.android.com/details?id=$packageName"
                    )
                )
                dialog.cancel()
                startActivity(marketIntent)
                finish()
            }
            .setNegativeButton(
                "Exit"
            ) { dialog, _ ->
                dialog.cancel()
                finish()
            }
            .setNeutralButton(
                "Re-Check"
            ) { _, _ ->
                if (isInternetAvailable(this)) {
                    doCheck()
                }
            }
            .setCancelable(false)
            .setOnKeyListener { dialog, i, _ ->
                Log.i("License", "Key Listener")
                dialog.cancel()
                finish()
                true
            }
        alertDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mChecker?.onDestroy()
    }

}

