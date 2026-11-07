export function scaled(str, scale) {
    const neg = str.startsWith("-");
    const [i, d = ""] = str.replace("-", "").split(".");
    const v = BigInt(i + d.padEnd(scale, "0"));

    return neg ? -v : v;
}

export function decimals(str) {
    return (str.split(".")[1] || "").length;
}

export function isPointInside(xStr, yStr, rStr) {
    // Extra decimal place makes R / 2 exact.
    const s =
        Math.max(
            decimals(xStr),
            decimals(yStr),
            decimals(rStr)
        ) + 1;

    const X = scaled(xStr, s);
    const Y = scaled(yStr, s);
    const R = scaled(rStr, s);
    const H = R / 2n;

    // First quadrant: quarter circle, radius R/2
    if (X >= 0n && Y >= 0n) {
        return X * X + Y * Y <= H * H;
    }

    // Fourth quadrant: rectangle, x in [0, R/2], y in [-R, 0]
    if (X >= 0n && Y <= 0n) {
        return X <= H && Y >= -R;
    }

    // Third quadrant: triangle (-R, 0), (0, 0), (0, -R/2)
    // hypotenuse is y = -(x + R) / 2; the point must be on or above it
    if (X <= 0n && Y <= 0n) {
        return X >= -R && 2n * Y >= -(X + R);
    }

    // Second quadrant: outside
    return false;
}

export function validateForm({
    xSelect,
    yInput,
    selectedR,
    showError,
    clearError
}) {
    clearError();

    const xRaw = xSelect.value;
    const yRaw = yInput.value.trim();

    if (xRaw === "" || !Number.isFinite(Number(xRaw))) {
        showError("Некорректное значение X.");
        return null;
    }

    const x = Number(xRaw);

    if (x < -4 || x > 4) {
        showError("X должен быть в диапазоне от -4 до 4.");
        return null;
    }

    if (yRaw === "") {
        showError("Поле Y не может быть пустым.");
        return null;
    }

    if (!/^-?\d+(\.\d+)?$/.test(yRaw)) {
        showError(
            "Y должен быть числом (только цифры, точка и знак «минус»)."
        );
        return null;
    }

    const y = Number(yRaw);
    const scale = decimals(yRaw) + 1;
    const Yb = scaled(yRaw, scale);

    if (
        Yb < scaled("-3", scale) ||
        Yb > scaled("3", scale)
    ) {
        showError("Y должен быть в диапазоне от -3 до 3.");
        return null;
    }

    if (selectedR === null) {
        showError("Выберите значение R.");
        return null;
    }

    return {
        x,
        y,
        yString: yRaw,
        xString: xRaw,
        rString: String(selectedR),
        r: selectedR
    };
}