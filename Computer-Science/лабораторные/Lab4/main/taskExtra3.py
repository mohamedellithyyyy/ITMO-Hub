# taskExtra3.py
import pickle

def dict_to_xml(data, root_tag="Schedule", indent=0):
    """
    Convert dictionary to XML manually.
    - root_tag: root element name
    - indent: for pretty printing
    """
    xml_lines = []
    prefix = "  " * indent
    if indent == 0:
        xml_lines.append(f"<{root_tag}>")

    for key, value in data.items():
        if isinstance(value, dict):
            xml_lines.append(f"{prefix}  <{key}>")
            xml_lines.extend(dict_to_xml(value, root_tag="", indent=indent+2))
            xml_lines.append(f"{prefix}  </{key}>")
        else:
            xml_lines.append(f"{prefix}  <{key}>{value}</{key}>")

    if indent == 0:
        xml_lines.append(f"</{root_tag}>")

    return xml_lines

# -----------------------------
# Load binary object
with open("Schedule.bin", "rb") as f:
    data = pickle.load(f)

# Convert to XML
xml_lines = dict_to_xml(data)
xml_text = "\n".join(xml_lines)

# Save XML to file
with open("Schedule_manual.xml", "w", encoding="utf-8") as f:
    f.write(xml_text)

print("Saved manual XML to Schedule_manual.xml")
