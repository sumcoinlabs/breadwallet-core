package com.breadwallet.crypto.jni.crypto;

import com.breadwallet.crypto.jni.CryptoLibrary;
import com.google.common.primitives.UnsignedBytes;

public interface CoreBRCryptoUnit {

    static CoreBRCryptoUnit createAsBase(CoreBRCryptoCurrency currency, String name, String symbol) {
        BRCryptoCurrency coreCurrency = currency.asBRCryptoCurrency();
        return new OwnedBRCryptoUnit(CryptoLibrary.INSTANCE.cryptoUnitCreateAsBase(coreCurrency, name, symbol));
    }

    static CoreBRCryptoUnit create(CoreBRCryptoCurrency currency, String name, String symbol, CoreBRCryptoUnit base, int decimals) {
        BRCryptoCurrency coreCurrency = currency.asBRCryptoCurrency();
        BRCryptoUnit coreBaseUnit = base.asBRCryptoUnit();
        byte decimalsAsByte = UnsignedBytes.checkedCast(decimals);
        return new OwnedBRCryptoUnit(CryptoLibrary.INSTANCE.cryptoUnitCreate(coreCurrency, name, symbol, coreBaseUnit, decimalsAsByte));
    }

    String getName();

    String getSymbol();

    int getDecimals();

    boolean isCompatible(CoreBRCryptoUnit other);

    boolean hasCurrency(CoreBRCryptoCurrency currency);

    BRCryptoUnit asBRCryptoUnit();
}