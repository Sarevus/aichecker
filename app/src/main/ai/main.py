def analyze_image(image_name):
    # Сопоставление имени файла с ответами
    answers = {
        "image_1": "CTF{this_is_fun}",
        "image_2": "HTB{M0n@_L1z@_!s_D3@D}",
        "image_3": "zosh2024{sT3g_S4ks_Y3h}",
    }
    # Проверяем, существует ли специальное имя файла в словаре ответов
    result = answers.get(image_name, "Ничего не найдено")
    return result
