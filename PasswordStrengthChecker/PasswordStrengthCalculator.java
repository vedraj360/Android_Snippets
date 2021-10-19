package in.vdx.sample;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.vdx.sample.R;
import kotlin.jvm.internal.Intrinsics;

public final class PasswordStrengthCalculator implements TextWatcher {
    @NotNull
    private MutableLiveData<StrengthLevel> strengthLevel = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Integer> strengthColor = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Integer> lowerCase = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Integer> upperCase = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Integer> digit = new MutableLiveData<>();
    @NotNull
    private MutableLiveData<Integer> specialChar = new MutableLiveData<>();

    @NotNull
    public final MutableLiveData<StrengthLevel> getStrengthLevel() {
        return this.strengthLevel;
    }

    public final void setStrengthLevel(@NotNull MutableLiveData<StrengthLevel> var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.strengthLevel = var1;
    }

    @NotNull
    public final MutableLiveData<Integer> getStrengthColor() {
        return this.strengthColor;
    }

    public final void setStrengthColor(@NotNull MutableLiveData<Integer> var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.strengthColor = var1;
    }

    @NotNull
    public final MutableLiveData<Integer> getLowerCase() {
        return this.lowerCase;
    }

    public final void setLowerCase(@NotNull MutableLiveData<Integer> var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.lowerCase = var1;
    }

    @NotNull
    public final MutableLiveData<Integer> getUpperCase() {
        return this.upperCase;
    }

    public final void setUpperCase(@NotNull MutableLiveData<Integer> var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.upperCase = var1;
    }

    @NotNull
    public final MutableLiveData<Integer> getDigit() {
        return this.digit;
    }

    public final void setDigit(@NotNull MutableLiveData<Integer> var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.digit = var1;
    }

    @NotNull
    public final MutableLiveData<Integer> getSpecialChar() {
        return this.specialChar;
    }

    public final void setSpecialChar(@NotNull MutableLiveData<Integer> var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.specialChar = var1;
    }

    public void afterTextChanged(@Nullable Editable p0) {
    }

    public void beforeTextChanged(@Nullable CharSequence p0, int p1, int p2, int p3) {
    }

    public void onTextChanged(@Nullable CharSequence var1, int p1, int p2, int p3) {
        if (var1 != null) {
            int isLower = this.hasLowerCase(var1.toString().trim()) ? 1 : 0;
            int isUpper = this.hasUpperCase(var1.toString().trim()) ? 1 : 0;
            int isDigit = this.hasDigit(var1.toString().trim()) ? 1 : 0;
            int isSpecial = this.hasSpecialChar(var1.toString().trim()) ? 1 : 0;
            String TAG = "ASDSADSA";
            Log.e(TAG, "onTextChanged: " + isLower);
            Log.e(TAG, "onTextChanged: " + isUpper);
            Log.e(TAG, "onTextChanged: " + isDigit);
            Log.e(TAG, "onTextChanged: specuial " + isSpecial);
            Log.e(TAG, "" + "\n");


            this.lowerCase.setValue(isLower);
            this.upperCase.setValue(isUpper);
            this.digit.setValue(isDigit);
            this.specialChar.setValue(isSpecial);
            this.calculateStrength(var1.toString().trim());
        }

    }

    private void calculateStrength(CharSequence password) {
        int passwordLength = password.length();

        if (0 == password.length()) {
            this.strengthColor.setValue(R.color.black);
            this.strengthLevel.setValue(StrengthLevel.EMPTY);
        } else if (passwordLength >= 1 && passwordLength <= 7) {
            this.strengthColor.setValue(R.color.weak);
            this.strengthLevel.setValue(PasswordStrengthCalculator.StrengthLevel.WEAK);
        } else if (passwordLength >= 8 && passwordLength <= 10) {
            if (this.lowerCase.getValue() == 1 || this.upperCase.getValue() == 1 || this.digit.getValue() == 1 || this.specialChar.getValue() == 1) {
                this.strengthColor.setValue(R.color.fair);
                this.strengthLevel.setValue(PasswordStrengthCalculator.StrengthLevel.FAIR);
            } else {
                this.strengthColor.setValue(R.color.weak);
                this.strengthLevel.setValue(PasswordStrengthCalculator.StrengthLevel.WEAK);
            }
        } else if (passwordLength >= 11 && passwordLength <= 15) {
            if (this.lowerCase.getValue() == 1 || this.upperCase.getValue() == 1 || this.digit.getValue() == 1 || this.specialChar.getValue() == 1) {
                if (this.lowerCase.getValue() == 1 && this.upperCase.getValue() == 1) {
                    this.strengthColor.setValue(R.color.good);
                    this.strengthLevel.setValue(PasswordStrengthCalculator.StrengthLevel.GOOD);
                }
            }
        } else if (passwordLength > 15) {
            if (this.lowerCase.getValue() == 1 && this.upperCase.getValue() == 1 && this.digit.getValue() == 1 && this.specialChar.getValue() == 1) {
                this.strengthColor.setValue(R.color.strong);
                this.strengthLevel.setValue(PasswordStrengthCalculator.StrengthLevel.STRONG);
            } else if (this.lowerCase.getValue() == 1 && this.upperCase.getValue() == 1) {

                this.strengthColor.setValue(R.color.good);
                this.strengthLevel.setValue(PasswordStrengthCalculator.StrengthLevel.GOOD);
            } else {
                this.strengthColor.setValue(R.color.fair);
                this.strengthLevel.setValue(PasswordStrengthCalculator.StrengthLevel.FAIR);
            }
        }
    }

    private boolean hasLowerCase(CharSequence $this$hasLowerCase) {
        Pattern var10000 = Pattern.compile("[a-z]");
        Intrinsics.checkNotNullExpressionValue(var10000, "Pattern.compile(\"[a-z]\")");
        Matcher var4 = var10000.matcher($this$hasLowerCase);
        Intrinsics.checkNotNullExpressionValue(var4, "pattern.matcher(this)");
        return var4.find();
    }

    private boolean hasUpperCase(CharSequence $this$hasUpperCase) {
        Pattern var10000 = Pattern.compile("[A-Z]");
        Intrinsics.checkNotNullExpressionValue(var10000, "Pattern.compile(\"[A-Z]\")");
        Matcher var4 = var10000.matcher($this$hasUpperCase);
        Intrinsics.checkNotNullExpressionValue(var4, "pattern.matcher(this)");
        return var4.find();
    }

    private boolean hasDigit(CharSequence $this$hasDigit) {
        Pattern var10000 = Pattern.compile("[0-9]");
        Intrinsics.checkNotNullExpressionValue(var10000, "Pattern.compile(\"[0-9]\")");
        Matcher var4 = var10000.matcher($this$hasDigit);
        Intrinsics.checkNotNullExpressionValue(var4, "pattern.matcher(this)");
        return var4.find();
    }

    private boolean hasSpecialChar(CharSequence $this$hasSpecialChar) {
        Pattern var10000 = Pattern.compile("[!@#$%^&*()_=+{}/.<>|\\[\\]~-]");
        Intrinsics.checkNotNullExpressionValue(var10000, "Pattern.compile(\"[!@#$%^&*()_=+{}/.<>|\\\\[\\\\]~-]\")");
        Matcher var4 = var10000.matcher($this$hasSpecialChar);
        Intrinsics.checkNotNullExpressionValue(var4, "pattern.matcher(this)");
        return var4.find();
    }

    public enum StrengthLevel {
        EMPTY,
        WEAK,
        FAIR,
        GOOD,
        STRONG;
    }
}
