def read_input():
    user_input = input("\nВведите 7-битную последовательность: ").strip()
    if user_input.lower() == 'exit':
        return None
    return user_input

def is_valid_input(bits):
    if len(bits) != 7 and not all(bit in '01' for bit in bits):
        return False, "Ошибка: требуется 7 бит и разрешены только символы '0' и '1'"
    if len(bits) != 7:
        return False, "Ошибка: требуется 7 бит"
    if not all(bit in '01' for bit in bits):
        return False, "Ошибка: разрешены только символы '0' и '1'"
    return True, "Валидные данные"

def calculate_syndromes(received):
    
    s1 = received[0] ^ received[2] ^ received[4] ^ received[6]
    s2 = received[1] ^ received[2] ^ received[5] ^ received[6] 
    s3 = received[3] ^ received[4] ^ received[5] ^ received[6] 
    return s1, s2, s3


def find_error_position(s1, s2, s3):
    return s1 * 1 + s2 * 2 + s3 * 4

def correct_error(received, error_position):
    corrected = received.copy()
    if error_position != 0:
        corrected[error_position - 1] = 1 - corrected[error_position - 1]
    return corrected

def extract_info_bits(corrected):
    info_bits = [corrected[2], corrected[4], corrected[5], corrected[6]]
    return ''.join(str(bit) for bit in info_bits)


def print_result(result):

    print(f"\nРезультат декодирования:")
    print(f"Вход:          {result['input']}")
    print(f"Исправлено:    {result['corrected_message']}")
    print(f"Инф. биты:     {result['info_bits']}")
    
    if result['has_error']:
        print(f"Обнаружена ошибка в бите: {result['error_position']}")
    else:
        print("Ошибок не обнаружено")

def hamming_7_4_decode(bits):

    # Проверка валидности входных данных
    is_valid, message = is_valid_input(bits)
    if not is_valid:
        return {"error": message}
    received = [int(bit) for bit in bits]
    s1, s2, s3 = calculate_syndromes(received)
    error_position = find_error_position(s1, s2, s3)
    corrected = correct_error(received, error_position)
    info_bits = extract_info_bits(corrected)
    result = {
        'input': bits,
        'corrected_message': ''.join(str(bit) for bit in corrected),
        'info_bits': info_bits,
        'error_position': error_position if error_position != 0 else None,
        'has_error': error_position != 0,
        'syndromes': (s1, s2, s3)
    }
    return result

def main():
    """
    Основная функция программы
    """
    print("Декодер кода Хэмминга (7,4)")
    print("Формат: 7 бит, например: 1011001")
    print("Выход: информационные биты + позиция ошибки")
    print("Для выхода введите 'Exit'")
    print("-" * 50)
    
    
    while True:
        user_input = read_input()
        
        if user_input is None:  # 'q' для выхода
            print("Выход из программы...")
            break
        
        if user_input is False:  # Невалидный ввод
            continue
        
        result = hamming_7_4_decode(user_input)
        
        if 'error' in result:
            print(result['error'])
        else:
            print_result(result)

if __name__ == "__main__":
    main()
