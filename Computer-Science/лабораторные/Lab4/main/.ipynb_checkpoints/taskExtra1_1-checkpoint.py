import pickle

def dict_to_yaml(data, indent=0):
    """Convert dictionary to YAML-like format manually."""
    lines = []
    for key, value in data.items():
        prefix = "  " * indent
        if isinstance(value, dict):
            lines.append(f"{prefix}{key}:")
            lines.extend(dict_to_yaml(value, indent + 1))
        else:
            lines.append(f"{prefix}{key}: {value}")
    return lines

# Load binary object
with open("Schedule.bin", "rb") as f:
    data = pickle.load(f)

# Convert to YAML
yaml_lines = dict_to_yaml(data)
yaml_text = "\n".join(yaml_lines)

# Save to file
with open("Schedule.yaml", "w", encoding="utf-8") as f:
    f.write(yaml_text)

print("Saved YAML format to Schedule.yaml")
