package com.david.farmatodoprueba.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Calculate {

    public ResultEcuacion calculate(String ecuacion) {
        ResultEcuacion resultEcuacion = new ResultEcuacion();

        if (ecuacion != null || !ecuacion.isEmpty()) {

            ecuacion = ecuacion.replace("×", "*");
            ecuacion = ecuacion.replace("x", "*");
            ecuacion = ecuacion.replace("X", "*");
            ecuacion = ecuacion.replace("÷", "/");

            boolean expressionError = false;

            for (int i = 0; i < ecuacion.length(); i++) {
                char caracter = ecuacion.charAt(i);

                if (!Character.isDigit(caracter) && caracter != '(' && caracter != ')') {
                    if (caracter != ' ') {
                        if (!expressionError) {
                            expressionError = true;
                        } else {
                            if (caracter == '-') {
                                char characterSig = ecuacion.charAt(i + 1);
                                if (!Character.isDigit(characterSig) && characterSig != '(' && characterSig != '[') {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                } else {
                    expressionError = false;
                }

            }

            if (!expressionError) {
                try {
                    SQLiteDatabase sqLiteDatabase = SQLiteDatabase.create(null);
                    Cursor cursor = sqLiteDatabase.rawQuery("select " + ecuacion, null);
                    cursor.moveToFirst();

                    resultEcuacion.setResult(cursor.getString(0));
                    resultEcuacion.setMultiple(getMultiple(cursor.getInt(0)));
                    resultEcuacion.setSuccess(true);

                    cursor.close();

                    return resultEcuacion;
                } catch (SQLiteException e) {
                    resultEcuacion.setSuccess(false);
                    return resultEcuacion;
                }
            } else {
                resultEcuacion.setSuccess(false);
                return resultEcuacion;
            }

        }
        resultEcuacion.setSuccess(false);
        return resultEcuacion;
    }

    public int getMultiple(int result) {
        if (result == 0) {
            return 0;
        }

        if ((result % 3) == 0) {
            return 3;
        }

        if ((result % 5) == 0) {
            return 5;
        }

        if ((result % 7) == 0) {
            return 7;
        }

        if ((result % 11) == 0) {
            return 11;
        }

        if ((result % 13) == 0) {
            return 13;
        }

        return -1;
    }
}
