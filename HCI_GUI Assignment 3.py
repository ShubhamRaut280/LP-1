import tkinter as tk

# Function to update the display
def press(key):
    display.insert(tk.END, key)

# Function to evaluate the expression
def calculate():
    try:
        result = eval(display.get())
        display.delete(0, tk.END)
        display.insert(tk.END, result)
    except:
        display.delete(0, tk.END)
        display.insert(tk.END, "Error")

# Function to clear the display
def clear_display():
    display.delete(0, tk.END)

# Create the main window
root = tk.Tk()
root.title("Simple Calculator")

# Create a text entry for the display
display = tk.Entry(root, width=25, font=('Arial', 20), bd=5, justify=tk.RIGHT)
display.grid(row=0, column=0, columnspan=4)

# Buttons for numbers and operators
buttons = [
    '7', '8', '9', '/',
    '4', '5', '6', '*',
    '1', '2', '3', '-',
    'C', '0', '=', '+'
]

# Define button properties and layout
row = 1
col = 0
for button in buttons:
    if button != '=' and button != 'C':
        btn = tk.Button(root, text=button, width=5, height=2, font=('Arial', 15), command=lambda key=button: press(key))
    elif button == '=':
        btn = tk.Button(root, text=button, width=5, height=2, font=('Arial', 15), command=calculate)
    else:
        btn = tk.Button(root, text=button, width=5, height=2, font=('Arial', 15), command=clear_display)

    btn.grid(row=row, column=col)
    col += 1
    if col > 3:
        col = 0
        row += 1

# Start the main loop
root.mainloop()
