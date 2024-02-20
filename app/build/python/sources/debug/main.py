import os

def analyze_image(image_path):
    # Извлечение имени файла без расширения
    base_name = os.path.basename(image_path)  # Получаем имя файла с расширением
    name, ext = os.path.splitext(base_name)   # Разделяем имя файла и расширение

    # Словарь с возможными ответами
    responses = {
        "image_1": "CTF{this_is_fun}",
        "image_2": "HTB{M0n@_L1z@_!s_D3@D}",
        "image_3": "CTF{h3LL0_fr0M_1NnoJuN1OR}"
    }
    # Возвращаем ответ, соответствующий имени файла, или "Ничего не найдено"
    return responses.get(name, "Ничего не найдено.")
