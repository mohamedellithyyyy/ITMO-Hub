# Informatics_Lab3_Task3.py
import re

def solve(text, word_number):
    if word_number <= 0:
        return "Invalid word number."
    endings = [
        "ого","его","ому","ему","ыми","ими", 
        "ая","яя","ое","ее","ые","ие",
        "ый","ий","ой","ых","их","ым","им","ом","ем","ую"
    ]
    endings = sorted(set(endings), key=lambda s: -len(s))
    endings_pat = "(" + "|".join(re.escape(e) for e in endings) + r")$"
    adj_re = re.compile(endings_pat, re.IGNORECASE)

    token_re = re.compile(r"\b[\w-]+\b", re.UNICODE)

    groups = {}  
    tokens = []  
    for m in token_re.finditer(text):
        tok = m.group(0)
        tokens.append((m.start(), m.end(), tok))
        em = adj_re.search(tok)
        if em:
            ending = em.group(1)
            base = tok[:-len(ending)].lower() 
            groups.setdefault(base, []).append(tok)

    replacements = {}  # base -> template_form (as-is from text)
    k = word_number
    for base, occ_list in groups.items():
        if len(occ_list) >= k:
            replacements[base] = occ_list[k-1]  

    result_parts = []
    last = 0
    for start, end, tok in tokens:
        result_parts.append(text[last:start])  
        em = adj_re.search(tok)
        if em:
            ending = em.group(1)
            base = tok[:-len(ending)].lower()
            if base in replacements:
                template = replacements[base]
        
                if tok[0].isupper():
                    repl = template[0].upper() + template[1:] if template else template
                else:
                    repl = template
                result_parts.append(repl)
            else:
                result_parts.append(tok)
        else:
            result_parts.append(tok)
        last = end

    result_parts.append(text[last:])
    return "".join(result_parts)

# tests
tests = [
    ("Футбольный клуб «Реал Мадрид» является 15-кратным обладателем "
    "главного футбольного европейского трофея – Лиги Чемпионов. "
    "Данный турнир организован Союзом европейских футбольных ассоциаций (УЕФА). "
    "Идея о континентальном футбольном турнире пришла к журналисту Габриэлю Ано в 1955 году.", 2),
    
    ("Маленький мальчик играл на маленьком дворе. "
    "Его мама наблюдала за маленьким ребенком из окна.", 2),
    
    ("Старый профессор читал лекцию в старой аудитории. "
    "Студенты уважали старого учителя за его знания.", 2),
    
    ("Красивый сад расположен рядом с красивым прудом. "
    "Посетители наслаждались красивым ландшафтом.", 2),
    
    ("Новый музей открылся в новом здании. "
    "Экспозиции в новом музее привлекали много туристов.", 2)
]
for i, (text, word_number) in enumerate(tests, 1):
    print(f"\n=== Test {i} ===")
    print("Original text:")
    print(text)
    print("\nProcessed text:")
    print(solve(text, word_number))
