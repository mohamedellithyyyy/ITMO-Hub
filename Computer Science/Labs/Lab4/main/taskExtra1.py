from task1 import parse

def to_yaml(json, file, indent=0):
    for key, value in json.items():
        
        if isinstance(key, str) and key.isdigit():
            display_key = f"'{key}'"
        else:
            display_key = key

        if isinstance(value, dict):
            file.write(" " * indent + f"{display_key}:\n")
            to_yaml(value, file, indent + 2)

        elif isinstance(value, list):
            file.write(" " * indent + f"{display_key}:\n")
            for item in value:
                if isinstance(item, dict):
                    file.write(" " * (indent + 2) + "-\n")
                    to_yaml(item, file, indent + 4)
                else:
                    file.write(" " * (indent + 2) + f"- {item}\n")

        else:
            file.write(" " * indent + f"{display_key}: {value}\n")


with open("../data/Schedule.json", "r", encoding="utf-8") as f:
    json_text = f.read()

data = parse(json_text)

with open("../output/taskExtra1Result.yaml", "w", encoding="utf-8") as file:
    to_yaml(data, file)
