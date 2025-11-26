import pickle

def dict_to_toml(data):
    """Convert dictionary to TOML-like format manually."""
    lines = []
    for section, content in data.items():
        lines.append(f"[{section}]")
        for key, value in content.items():
            lines.append(f"{key} = \"{value}\"")  # wrap strings in quotes
        lines.append("")  # blank line between sections
    return lines

# Load binary object
with open("Schedule.bin", "rb") as f:
    data = pickle.load(f)

# Convert to TOML
toml_lines = dict_to_toml(data)
toml_text = "\n".join(toml_lines)

# Save to file
with open("Schedule.toml", "w", encoding="utf-8") as f:
    f.write(toml_text)

print("Saved TOML format to Schedule.toml")
