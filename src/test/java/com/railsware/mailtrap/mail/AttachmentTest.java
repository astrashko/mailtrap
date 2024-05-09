package com.railsware.mailtrap.mail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttachmentTest {
    @Test
    void inlineAttachmentTest() {
        Attachment attachment = new Attachment("PCFET0bWw+Cg==", Type.TEXT, "nature.jpeg", Disposition.INLINE, "nature.jpeg");
        assertEquals("PCFET0bWw+Cg==", attachment.content());
        assertEquals(Type.TEXT, attachment.type());
        assertEquals("nature.jpeg", attachment.filename());
        assertEquals(Disposition.INLINE, attachment.disposition());
        assertEquals("nature.jpeg", attachment.content_id());
    }

    @Test
    void attachmentTest() {
        Attachment attachment = new Attachment("PCFET0bWw+Cg==", Type.TEXT, "nature.jpeg", Disposition.ATTACHMENT, null);
        assertEquals("PCFET0bWw+Cg==", attachment.content());
        assertEquals(Type.TEXT, attachment.type());
        assertEquals("nature.jpeg", attachment.filename());
        assertEquals(Disposition.ATTACHMENT, attachment.disposition());
        assertNull(attachment.content_id());
    }

    @Test
    void shouldThrowExceptionDispositionNull() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new Attachment("PCFET0bWw+Cg==", Type.TEXT, "nature.jpeg", null, null);
        });
        assertEquals("disposition is marked non-null but is null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionFileNameNull() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new Attachment("PCFET0bWw+Cg==", Type.TEXT, null, Disposition.ATTACHMENT, null);
        });
        assertEquals("filename is marked non-null but is null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionTypeNull() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new Attachment("PCFET0bWw+Cg==", null, "nature.jpeg", Disposition.ATTACHMENT, null);
        });
        assertEquals("type is marked non-null but is null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionContentNull() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new Attachment(null, Type.TEXT, "nature.jpeg", Disposition.ATTACHMENT, null);
        });
        assertEquals("content is marked non-null but is null", exception.getMessage());
    }
}