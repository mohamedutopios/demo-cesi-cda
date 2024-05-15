from flask import Flask, request, redirect, url_for

app = Flask(__name__)

@app.route('/collect', methods=['GET', 'POST'])
def collect():
    if request.method == 'POST':
        u = request.form.get('u', 'unknown')
        p = request.form.get('p', 'unknown')
        # Effectuer une redirection après le POST vers la même route en GET
        return redirect(url_for('collect', u=u, p=p))
    else:
        u = request.args.get('u', 'unknown')
        p = request.args.get('p', 'unknown')
        return f'''
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Tu es Hacké</title>
        </head>
        <body>
            <h1>Contenu de l'URL</h1>
            <p>u={u}</p>
            <p>p={p}</p>
        </body>
        </html>
        '''

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
