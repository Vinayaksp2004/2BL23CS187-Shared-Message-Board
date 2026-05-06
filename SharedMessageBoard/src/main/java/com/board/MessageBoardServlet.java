package com.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MessageBoardServlet")
public class MessageBoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        @SuppressWarnings("unchecked")
        List<String> msgs = (List<String>) getServletContext().getAttribute("messages");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Public Message Board</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; margin: 0; padding: 20px; display: flex; flex-direction: column; align-items: center; }");
        out.println(".container { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); width: 100%; max-width: 600px; }");
        out.println("h1 { color: #333; text-align: center; }");
        out.println(".message-list { list-style: none; padding: 0; margin-top: 20px; }");
        out.println(".message-item { background: #fff; border-left: 5px solid #007bff; margin-bottom: 10px; padding: 15px; border-radius: 4px; box-shadow: 0 2px 4px rgba(0,0,0,0.05); }");
        out.println(".message-text { color: #444; font-size: 1.1rem; }");
        out.println(".message-author { color: #888; font-size: 0.9rem; margin-top: 5px; font-weight: bold; }");
        out.println(".back-link { display: inline-block; margin-top: 20px; color: #007bff; text-decoration: none; font-weight: bold; }");
        out.println(".back-link:hover { text-decoration: underline; }");
        out.println(".no-messages { text-align: center; color: #999; font-style: italic; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Public Notice Board</h1>");

        if (msgs == null || msgs.isEmpty()) {
            out.println("<p class='no-messages'>No messages yet. Be the first to post!</p>");
        } else {
            out.println("<ul class='message-list'>");
            for (String m : msgs) {
                // Assuming format "Name: Message"
                int colonIndex = m.indexOf(":");
                String author = (colonIndex != -1) ? m.substring(0, colonIndex) : "Anonymous";
                String text = (colonIndex != -1) ? m.substring(colonIndex + 1).trim() : m;

                out.println("<li class='message-item'>");
                out.println("<div class='message-text'>\"" + escapeHtml(text) + "\"</div>");
                out.println("<div class='message-author'>— " + escapeHtml(author) + "</div>");
                out.println("</li>");
            }
            out.println("</ul>");
        }

        out.println("<a href='index.html' class='back-link'>&larr; Post a New Message</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String msg = request.getParameter("message");

        if (name != null && !name.trim().isEmpty() && msg != null && !msg.trim().isEmpty()) {
            synchronized (getServletContext()) {
                @SuppressWarnings("unchecked")
                List<String> msgs = (List<String>) getServletContext().getAttribute("messages");
                if (msgs == null) {
                    msgs = new ArrayList<>();
                }
                // Append the new message (name + message text) to the list at index 0 (latest first)
                msgs.add(0, name + ": " + msg);
                getServletContext().setAttribute("messages", msgs);
            }
        }

        // After processing, show the messages
        doGet(request, response);
    }

    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
